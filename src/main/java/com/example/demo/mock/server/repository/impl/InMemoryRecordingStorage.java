package com.example.demo.mock.server.repository.impl;

import com.example.demo.mock.server.domain.record.InMemoryStoredRecord;
import com.example.demo.mock.server.repository.RequestResponseStorage;
import com.example.demo.mock.server.repository.StorageQualifiers;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OTARANOVSKYI on 26.07.2017.
 */
@Component
@Qualifier(StorageQualifiers.IN_MEMORY)
public class InMemoryRecordingStorage implements RequestResponseStorage<InMemoryStoredRecord> {

    private List<InMemoryStoredRecord> inMemoryStoredRecords = new ArrayList<>();

    private void put(HttpRequest requestData, HttpResponse responseData, long delay) {
        InMemoryStoredRecord inMemoryStoredRecord = new InMemoryStoredRecord();
        inMemoryStoredRecord.setRequest(requestData);
        inMemoryStoredRecord.setResponse(responseData);
        inMemoryStoredRecord.setDelay(delay);

        inMemoryStoredRecords.add(inMemoryStoredRecord);
    }

    @Override
    public void saveRequestAndResponse(HttpRequest requestData, HttpResponse responseData, long delay) {
        put(requestData, responseData, delay);
    }

    @Override
    public Iterable<InMemoryStoredRecord> getAllRecords() {
        return inMemoryStoredRecords;
    }

    @Override
    public void reset() {
        inMemoryStoredRecords.clear();
    }
}
