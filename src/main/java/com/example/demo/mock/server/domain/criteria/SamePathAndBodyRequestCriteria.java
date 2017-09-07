package com.example.demo.mock.server.domain.criteria;

import com.example.demo.mock.server.domain.RequestCriteria;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.mockserver.model.HttpRequest;

public class SamePathAndBodyRequestCriteria implements RequestCriteria {

    private final HttpRequest request;

    public SamePathAndBodyRequestCriteria(HttpRequest request) {
        this.request = request;
    }

    @Override
    public boolean satisfiedBy(HttpRequest request) {
        return new EqualsBuilder()
                .append(this.request.getPath(), request.getPath())
                .append(this.request.getBody().getValue(), request.getBody().getValue())
                .isEquals();
    }
}
