package com.example.demo.mock.server.domain;

import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
public class ResponseData {
    private Map<String, Object> response;
    private Map<String, Object> body;

    public void setResponse(Map<String, Object> response) {
        this.response = response;
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public Map<String, Object> getBody() {
        return body;
    }
}
