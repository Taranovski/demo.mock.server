package com.example.demo.mock.server.converter.body.handler;

import com.example.demo.mock.server.converter.body.BodyExtractorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by OTARANOVSKYI on 31.07.2017.
 */
@Component
public class JsonBodyExtractorHandler implements BodyExtractorHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Map<String, Object> convertBody(String value) {
        try {
            return objectMapper.readValue(value, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
