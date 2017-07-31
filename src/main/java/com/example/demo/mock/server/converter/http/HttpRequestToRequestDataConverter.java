package com.example.demo.mock.server.converter.http;

import com.example.demo.mock.server.util.WithObjectMapper;
import com.example.demo.mock.server.converter.log.RequestDataProducer;
import com.example.demo.mock.server.domain.RequestData;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by OTARANOVSKYI on 31.07.2017.
 */
@Component
public class HttpRequestToRequestDataConverter {

    @Autowired
    RequestDataProducer requestDataProducer;

    public RequestData createRequestData(HttpRequest httpRequest) {
        Map<String, Object> request = null;
        try {
            request = WithObjectMapper.OBJECT_MAPPER.readValue(httpRequest.toString(), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return requestDataProducer.createRequestData(request);
    }
}
