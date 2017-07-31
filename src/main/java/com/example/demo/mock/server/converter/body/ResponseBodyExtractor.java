package com.example.demo.mock.server.converter.body;

import com.example.demo.mock.server.converter.body.extractor.JsonBodyExtractor;
import com.example.demo.mock.server.converter.body.extractor.XmlBodyExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * Created by OTARANOVSKYI on 31.07.2017.
 */
@Component
public class ResponseBodyExtractor {
    private static final String BODY = "body";

    @Autowired
    private XmlBodyExtractor xmlBodyExtractorHandler;

    @Autowired
    private JsonBodyExtractor jsonBodyExtractorHandler;

    public Map<String, Object> getBody(Map<String, Object> content) {
        if (content.isEmpty() || !content.containsKey(BODY)) {
            return Collections.emptyMap();
        }

        String string = (String) content.get(BODY);

        if (looksLikeXml(string)) {
            return xmlBodyExtractorHandler.convertBody(string);
        }
        if (looksLikeJson(string)) {
            return jsonBodyExtractorHandler.convertBody(string);
        }

        throw new RuntimeException("unknown body type: " + string);
    }

    private boolean looksLikeJson(String string) {
        return (string.startsWith("{") && string.endsWith("}")) || (string.startsWith("[") && string.endsWith("]"));
    }

    private boolean looksLikeXml(String string) {
        return string.startsWith("<") && string.endsWith(">");
    }
}
