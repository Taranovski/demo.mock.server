package com.example.demo.mock.server.converter;

import com.example.demo.mock.server.domain.ResponseData;
import org.mockserver.model.HttpResponse;
import org.springframework.stereotype.Component;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class HttpResponseProducer {
    public HttpResponse createHttpResponse(ResponseData responseData) {
        return null;
    }
}