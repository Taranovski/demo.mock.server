package com.example.demo.mock.server.converter.body;

import com.example.demo.mock.server.converter.body.handler.StringBodyExtractorHandler;
import com.example.demo.mock.server.converter.body.handler.XmlBodyExtractorHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class RequestBodyExtractor {

    public static final String BODY = "body";
    public static final String VALUE = "value";
    public static final String TYPE = "type";

    private Map<String, BodyExtractorHandler> handlers;

    @PostConstruct
    public void init() {
        handlers = new HashMap<>();

        handlers.put("STRING", new StringBodyExtractorHandler());
        handlers.put("XML", new XmlBodyExtractorHandler());
    }


    public Map<String, Object> getBody(Map<String, Object> content) {
        if (content.isEmpty() || !content.containsKey(BODY)) {
            return Collections.emptyMap();
        }

        Map<String, Object> body = (Map<String, Object>) content.get(BODY);

        if (body == null || body.isEmpty()) {
            return null;
        }

        String type = (String) body.get(TYPE);
        String value = (String) body.get(VALUE);

        BodyExtractorHandler bodyExtractorHandler = handlers.get(type);

        if (bodyExtractorHandler != null) {
            return bodyExtractorHandler.convertBody(value);
        }

        throw new RuntimeException("unknown type" + type);

    }
}
