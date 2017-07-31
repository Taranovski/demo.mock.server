package com.example.demo.mock.server.converter.body.extractor;

import com.example.demo.mock.server.converter.body.BodyExtractor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class XmlBodyExtractor implements BodyExtractor {
    @Override
    public Map<String, Object> convertBody(String value) {
        JSONObject jsonObject = XML.toJSONObject(value);

        return jsonObject.toMap();
    }
}
