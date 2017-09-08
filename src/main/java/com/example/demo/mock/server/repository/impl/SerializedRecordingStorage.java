package com.example.demo.mock.server.repository.impl;

import com.example.demo.mock.server.domain.record.SerializedStoredRecord;
import com.example.demo.mock.server.repository.RequestResponseStorage;
import com.example.demo.mock.server.repository.SerializationProvider;
import com.example.demo.mock.server.repository.StorageQualifiers;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by OTARANOVSKYI on 02.08.2017.
 */
@Component
@Qualifier(StorageQualifiers.SERIALIZED)
public class SerializedRecordingStorage implements RequestResponseStorage<SerializedStoredRecord> {

    private List<byte[]> requestsAndResponses = new ArrayList<>();

    @Autowired
    private SerializationProvider serializationProvider;

    @Override
    public void saveRequestAndResponse(HttpRequest requestData, HttpResponse responseData, long delay) {
        byte[] bytes = serializationProvider.saveToBytes(requestData, responseData, delay);
        requestsAndResponses.add(bytes);
    }

    @Override
    public Stream<SerializedStoredRecord> getAllRecords() {
        return requestsAndResponses.stream()
                .map(serializationProvider::getFromBytes);
    }

    @Override
    public void reset() {
        requestsAndResponses.clear();
    }
}
