package com.example.demo.mock.server.repository;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.ByteBufferInput;
import com.esotericsoftware.kryo.io.ByteBufferOutput;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.example.demo.mock.server.domain.record.SerializedStoredRecord;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.StringBody;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;

/**
 * Created by OTARANOVSKYI on 02.08.2017.
 */
@Component
public class SerializationProvider {
    private Kryo kryo;

    @PostConstruct
    public void init() {
        kryo = new Kryo();

        kryo.register(StringBody.class, new Serializer() {
            @Override
            public void write(Kryo kryo, Output output, Object object) {

            }

            @Override
            public Object read(Kryo kryo, Input input, Class type) {
                return null;
            }
        });

    }

    public byte[] saveToBytes(HttpRequest requestData, HttpResponse responseData, long delay) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteBufferOutput output = new ByteBufferOutput(byteArrayOutputStream);

        kryo.writeObject(output, requestData);
        kryo.writeObject(output, responseData);
        kryo.writeObject(output, delay);

        output.flush();

        return byteArrayOutputStream.toByteArray();
    }

    public SerializedStoredRecord getFromBytes(byte[] next) {
        Input input = new ByteBufferInput(next);
        return new SerializedStoredRecord(kryo, input);
    }
}
