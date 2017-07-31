package com.example.demo.mock.server.converter.log;

import com.example.demo.mock.server.util.WithObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class LogExtractor {

    public static final String REQUEST = "request:";
    public static final String RETURNING_RESPONSE = "returning response:";
    public static final String MATCHED_EXPECTATION = "matched expectation:";
    public static final String FOR_REQUEST = "for request:";

    public Map<String, Object> getRequest(String message) {
        return getStringObjectMap(message, REQUEST, MATCHED_EXPECTATION);
    }

    public Map<String, Object> getResponse(String message) {
        return getStringObjectMap(message, RETURNING_RESPONSE, FOR_REQUEST);
    }

    private Map<String, Object> getStringObjectMap(String message, String start, String end) {
        try {
            String substring = message.substring(message.indexOf(start) + start.length() + 1, message.lastIndexOf(end));
            return WithObjectMapper.OBJECT_MAPPER.readValue(substring, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
