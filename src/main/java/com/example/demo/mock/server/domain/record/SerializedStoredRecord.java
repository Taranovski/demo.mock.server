package com.example.demo.mock.server.domain.record;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.example.demo.mock.server.domain.StoredRecord;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

/**
 * Created by OTARANOVSKYI on 02.08.2017.
 */
public class SerializedStoredRecord implements StoredRecord {

    private final Kryo kryo;
    private int state = 0;
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private Long delay;
    private final Input input;

    public SerializedStoredRecord(Kryo kryo, Input input) {
        this.kryo = kryo;
        this.input = input;
    }

    @Override
    public HttpRequest getRequest() {
        return readHttpRequestOrGet();
    }

    private HttpRequest readHttpRequestOrGet() {
        if (state > 0) {
            return httpRequest;
        } else {
            httpRequest = kryo.readObject(input, HttpRequest.class);
            state = 1;
            return httpRequest;
        }
    }

    @Override
    public HttpResponse getResponse() {
        return readHttpResponseOrGet();
    }

    private HttpResponse readHttpResponseOrGet() {
        if (state > 1) {
            return httpResponse;
        } else {
            httpResponse = kryo.readObject(input, HttpResponse.class);
            state = 2;
            return httpResponse;
        }
    }

    @Override
    public Long getDelay() {
        return readDelayOrGet();
    }

    private Long readDelayOrGet() {
        if (state > 2) {
            return delay;
        } else {
            delay = kryo.readObject(input, Long.class);
            state = 3;
            input.close();
            return delay;
        }
    }
}
