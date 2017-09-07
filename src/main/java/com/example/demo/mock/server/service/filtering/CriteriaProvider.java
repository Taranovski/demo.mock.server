package com.example.demo.mock.server.service.filtering;

import com.example.demo.mock.server.domain.RequestCriteria;
import org.mockserver.model.HttpRequest;

public interface CriteriaProvider {
    RequestCriteria forRequest(HttpRequest httpRequest);
}
