package com.example.demo.mock.server.converter.body;

import com.example.demo.mock.server.converter.body.extractor.StringBodyExtractor;
import com.example.demo.mock.server.converter.body.extractor.XmlBodyExtractor;
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

    private Map<String, BodyExtractor> handlers;

    @PostConstruct
    public void init() {
        handlers = new HashMap<>();

        handlers.put("STRING", new StringBodyExtractor());
        handlers.put("XML", new XmlBodyExtractor());
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

        BodyExtractor bodyExtractor = handlers.get(type);

        if (bodyExtractor != null) {
            return bodyExtractor.convertBody(value);
        }

        throw new RuntimeException("unknown type" + type);

    }
}
