package com.example.demo.mock.server.repository;

import com.example.demo.mock.server.domain.StoredRecord;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

/**
 * Created by OTARANOVSKYI on 01.08.2017.
 */
public interface RequestResponseStorage<T extends StoredRecord> {
    void saveRequestAndResponse(HttpRequest requestData, HttpResponse responseData, long delay);
    Iterable<T> getAllRecords();
    void reset();
}
