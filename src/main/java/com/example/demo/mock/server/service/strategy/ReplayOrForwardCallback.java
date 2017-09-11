package com.example.demo.mock.server.service.strategy;

import org.mockserver.mock.action.ExpectationCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ReplayOrForwardCallback implements ExpectationCallback {

    @Autowired
    private ForwardCallback forwardCallback;

    @Autowired
    private ReplayCallback replayCallback;

    @Override
    public HttpResponse handle(HttpRequest httpRequest) {
        HttpResponse savedResponse = replayCallback.handle(httpRequest);
        if (savedResponse.getStatusCode() != HttpStatus.NOT_FOUND.value())
            return savedResponse;
        return forwardCallback.handle(httpRequest);
    }
}
