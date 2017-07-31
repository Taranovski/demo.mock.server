package com.example.demo.mock.server.domain.criteria;

import com.example.demo.mock.server.domain.RequestCriteria;
import com.example.demo.mock.server.domain.RequestData;

/**
 * Created by OTARANOVSKYI on 31.07.2017.
 */
public class SameRequestCriteria implements RequestCriteria {

    private final RequestData requestData;

    public SameRequestCriteria(RequestData requestData) {
        this.requestData = requestData;
    }

    @Override
    public boolean satisfiedBy(RequestData requestData) {
        return this.requestData.equals(requestData);
    }
}
