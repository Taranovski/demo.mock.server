package com.example.demo.mock.server.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
public class RequestData {
    private Map<String, Object> request;
    private Map<String, Object> body;
    private String host;
    private Integer port;

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public Map<String, Object> getRequest() {
        return request;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RequestData that = (RequestData) o;

        return new EqualsBuilder()
                .append(host, that.host)
                .append(port, that.port)
                .append(request, that.request)
                .append(body, that.body)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(request)
                .append(body)
                .append(host)
                .append(port)
                .toHashCode();
    }
}
