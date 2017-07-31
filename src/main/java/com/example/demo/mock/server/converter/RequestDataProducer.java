package com.example.demo.mock.server.converter;

import com.example.demo.mock.server.converter.body.BodyExtractor;
import com.example.demo.mock.server.domain.RequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class RequestDataProducer {

    @Autowired
    private BodyExtractor bodyExtractor;
    @Autowired
    private RequestExtractor requestExtractor;

    public RequestData createRequestData(Map map) {
        String message = getFormattedMessage(map);

        Map<String, Object> request = requestExtractor.getRequest(message);

        Map<String, Object> body = bodyExtractor.getBody(request);

        RequestData requestData = new RequestData();

        requestData.setRequest(request);
        requestData.setBody(body);

        return requestData;
    }

    private String getFormattedMessage(Map map) {
        return (String) map.get("formattedMessage");
    }
}
