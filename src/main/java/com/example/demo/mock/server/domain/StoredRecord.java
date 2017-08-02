package com.example.demo.mock.server.domain;

import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

/**
 * Created by OTARANOVSKYI on 02.08.2017.
 */
public interface StoredRecord {
    HttpRequest getRequest();

    HttpResponse getResponse();

    Long getDelay();
}
