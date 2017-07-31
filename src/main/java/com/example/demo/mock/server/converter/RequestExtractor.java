package com.example.demo.mock.server.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class RequestExtractor {

    public final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public Map<String, Object> getRequest(String message) {
        try {
            String substring = message.substring(message.indexOf("request:") + 9, message.lastIndexOf("matched expectation:"));
            return OBJECT_MAPPER.readValue(substring, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> getResponse(String message) {
        try {
            String substring = message.substring(message.indexOf("returning response:") + 21, message.indexOf("for request:"));
            return OBJECT_MAPPER.readValue(substring, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
