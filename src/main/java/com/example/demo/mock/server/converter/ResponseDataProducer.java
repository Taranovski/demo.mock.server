package com.example.demo.mock.server.converter;

import com.example.demo.mock.server.converter.body.BodyExtractor;
import com.example.demo.mock.server.domain.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class ResponseDataProducer {

    @Autowired
    private BodyExtractor bodyExtractor;
    @Autowired
    private RequestExtractor requestExtractor;

    public ResponseData createResponse(Map map) {
        String message = getFormattedMessage(map);

        Map<String, Object> response = requestExtractor.getResponse(message);

        Map<String, Object> body = bodyExtractor.getBody(response);

        ResponseData responseData = new ResponseData();

        responseData.setResponse(response);
        responseData.setBody(body);

        return responseData;
    }

    private String getFormattedMessage(Map map) {
        return (String) map.get("formattedMessage");
    }
}
