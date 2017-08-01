package com.example.demo.mock.server.domain.criteria;

import com.example.demo.mock.server.domain.RequestCriteria;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.mockserver.model.HttpRequest;

/**
 * Created by OTARANOVSKYI on 31.07.2017.
 */
public class HasCommonFieldsRequestCriteria implements RequestCriteria {

    private final HttpRequest request;

    public HasCommonFieldsRequestCriteria(HttpRequest request) {
        this.request = request;
    }

    @Override
    public boolean satisfiedBy(HttpRequest request) {
        return new EqualsBuilder()
                .append(this.request.getMethod(), request.getMethod())
                .append(this.request.getPath(), request.getPath())
                .append(this.request.getBody(), request.getBody())
                .append(this.request.isKeepAlive(), request.isKeepAlive())
                .append(this.request.isSecure(), request.isSecure())
                .append(this.request.isKeepAlive(), request.isKeepAlive())
                .append(this.request.getFirstHeader("Accept-Encoding"), request.getFirstHeader("Accept-Encoding"))
                .append(this.request.getFirstHeader("Accept"), request.getFirstHeader("Accept"))
                .append(this.request.getFirstHeader("SOAPAction"), request.getFirstHeader("SOAPAction"))
                .append(this.request.getFirstHeader("Host"), request.getFirstHeader("Host"))
                .append(this.request.getFirstHeader("SOAPAction"), request.getFirstHeader("SOAPAction"))
                .append(this.request.getFirstHeader("Content-Length"), request.getFirstHeader("Content-Length"))
                .isEquals();
    }
}
