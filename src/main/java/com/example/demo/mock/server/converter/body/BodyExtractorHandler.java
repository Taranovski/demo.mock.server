package com.example.demo.mock.server.converter.body;

import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
public interface BodyExtractorHandler {
    Map<String, Object> convertBody(String value);
}
