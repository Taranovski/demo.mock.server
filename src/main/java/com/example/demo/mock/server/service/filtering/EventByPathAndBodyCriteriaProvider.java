package com.example.demo.mock.server.service.filtering;

import com.example.demo.mock.server.domain.RequestCriteria;
import com.example.demo.mock.server.domain.criteria.SamePathAndBodyRequestCriteria;
import com.example.demo.mock.server.repository.StorageQualifiers;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(StorageQualifiers.FS_STORED)
public class EventByPathAndBodyCriteriaProvider implements CriteriaProvider {

    public RequestCriteria forRequest(HttpRequest httpRequest) {
        return new SamePathAndBodyRequestCriteria(httpRequest);
    }
}
