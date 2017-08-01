package com.example.demo.mock.server.domain;

import org.mockserver.model.HttpRequest;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
public interface RequestCriteria {

    boolean satisfiedBy(HttpRequest requestData);
}
