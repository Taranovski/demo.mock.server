package com.example.demo.mock.server.converter.http;

import com.example.demo.mock.server.domain.RequestData;
import org.mockserver.model.HttpRequest;
import org.springframework.stereotype.Component;

/**
 * Created by OTARANOVSKYI on 31.07.2017.
 */
@Component
public class HttpRequestToRequestDataConverter {
    public RequestData createRequestData(HttpRequest httpRequest) {
        return null;
    }
}
