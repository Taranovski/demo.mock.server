package com.example.demo.mock.server.service.search;

import com.example.demo.mock.server.domain.StoredRecord;
import com.example.demo.mock.server.repository.RequestResponseStorage;
import com.example.demo.mock.server.repository.StorageQualifiers;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class SearchByCriteriaService<R extends StoredRecord> {

    @Autowired
    @Qualifier(StorageQualifiers.FS_STORED)
    private RequestResponseStorage<R> recordingStorage;

    public HttpResponse find(Predicate<R> criteria) {
        return recordingStorage.getAllRecords()
                .filter(criteria)
                .findFirst()
                .map(StoredRecord::getResponse)
                .orElseGet(HttpResponse::notFoundResponse);
    }
}
