package com.example.demo.mock.server.converter;

import com.example.demo.mock.server.domain.RequestCriteria;
import org.mockserver.model.HttpRequest;
import org.springframework.stereotype.Component;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
@Component
public class EventCriteriaExtractor {
    public RequestCriteria getFromRequest(HttpRequest httpRequest) {
        return null;
    }
}
