package com.example.demo.mock.server.domain;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
public interface RequestCriteria {

    boolean satisfiedBy(RequestData requestData);
}
