package com.example.demo.mock.server.converter.body.extractor;

import com.example.demo.mock.server.converter.body.BodyExtractor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class StringBodyExtractor implements BodyExtractor {
    @Override
    public Map<String, Object> convertBody(String value) {
        return Collections.singletonMap("simple string value, map is not needed", value);
    }
}
