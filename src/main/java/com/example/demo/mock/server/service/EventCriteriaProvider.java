package com.example.demo.mock.server.service;

import com.example.demo.mock.server.domain.RequestCriteria;
import com.example.demo.mock.server.domain.criteria.HasCommonFieldsRequestCriteria;
import org.mockserver.model.HttpRequest;
import org.springframework.stereotype.Component;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
@Component
public class EventCriteriaProvider {

    public RequestCriteria forRequest(HttpRequest httpRequest) {
        return new HasCommonFieldsRequestCriteria(httpRequest);
    }
}
