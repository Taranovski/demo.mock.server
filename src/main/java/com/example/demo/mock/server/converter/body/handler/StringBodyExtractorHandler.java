package com.example.demo.mock.server.converter.body.handler;

import com.example.demo.mock.server.converter.body.BodyExtractorHandler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class StringBodyExtractorHandler implements BodyExtractorHandler {
    @Override
    public Map<String, Object> convertBody(String value) {
        return Collections.singletonMap("simple string value, map is not needed", value);
    }
}
