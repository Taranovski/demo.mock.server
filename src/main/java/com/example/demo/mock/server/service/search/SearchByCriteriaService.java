package com.example.demo.mock.server.service.search;

import com.example.demo.mock.server.domain.RequestCriteria;
import com.example.demo.mock.server.domain.StoredRecord;
import com.example.demo.mock.server.repository.RequestResponseStorage;
import com.example.demo.mock.server.repository.StorageQualifiers;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class SearchByCriteriaService {

    @Autowired
    @Qualifier(StorageQualifiers.IN_MEMORY)
    private RequestResponseStorage<? extends StoredRecord> recordingStorage;

    public HttpResponse find(RequestCriteria requestCriteria) {
        for (StoredRecord entry : recordingStorage.getAllRecords()) {
            if (requestCriteria.satisfiedBy(entry.getRequest())) {
                return entry.getResponse();
            }
        }

        return null;
    }
}
