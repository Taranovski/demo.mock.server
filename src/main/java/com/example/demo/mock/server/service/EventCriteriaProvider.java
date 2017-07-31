package com.example.demo.mock.server.service;

import com.example.demo.mock.server.converter.http.HttpRequestToRequestDataConverter;
import com.example.demo.mock.server.domain.RequestCriteria;
import com.example.demo.mock.server.domain.criteria.SameRequestCriteria;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
@Component
public class EventCriteriaProvider {

    @Autowired
    private HttpRequestToRequestDataConverter httpRequestToRequestDataConverter;

    public RequestCriteria forRequest(HttpRequest httpRequest) {
        return new SameRequestCriteria(httpRequestToRequestDataConverter.createRequestData(httpRequest));
    }
}
