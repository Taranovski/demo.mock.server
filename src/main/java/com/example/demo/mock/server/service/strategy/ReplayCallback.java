package com.example.demo.mock.server.service.strategy;

import com.example.demo.mock.server.repository.ResponseRepository;
import org.mockserver.mock.action.ExpectationCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReplayCallback implements ExpectationCallback {

    @Autowired
    private ResponseRepository responseRepository;

    @Override
    public HttpResponse handle(HttpRequest httpRequest) {
        return responseRepository.findResponseByCriteria(httpRequest);
    }
}
