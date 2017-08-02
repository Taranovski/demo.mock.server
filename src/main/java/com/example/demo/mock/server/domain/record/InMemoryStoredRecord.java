package com.example.demo.mock.server.domain.record;

import com.example.demo.mock.server.domain.StoredRecord;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

/**
 * Created by OTARANOVSKYI on 02.08.2017.
 */
public class InMemoryStoredRecord implements StoredRecord {
    private HttpRequest request;
    private HttpResponse response;
    private Long delay;

    @Override
    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    @Override
    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }

    @Override
    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }
}
