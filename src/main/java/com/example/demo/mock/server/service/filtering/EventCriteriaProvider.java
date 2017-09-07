package com.example.demo.mock.server.service.filtering;

import com.example.demo.mock.server.domain.RequestCriteria;
import com.example.demo.mock.server.domain.criteria.HasCommonFieldsRequestCriteria;
import com.example.demo.mock.server.repository.StorageQualifiers;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
@Component
@Qualifier(StorageQualifiers.IN_MEMORY)
public class EventCriteriaProvider implements CriteriaProvider {

    @Override
    public RequestCriteria forRequest(HttpRequest httpRequest) {
        return new HasCommonFieldsRequestCriteria(httpRequest);
    }
}
