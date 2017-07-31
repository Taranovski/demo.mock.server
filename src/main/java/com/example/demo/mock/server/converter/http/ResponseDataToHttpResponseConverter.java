package com.example.demo.mock.server.converter.http;

import com.example.demo.mock.server.util.WithObjectMapper;
import com.example.demo.mock.server.domain.ResponseData;
import org.mockserver.model.HttpResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class ResponseDataToHttpResponseConverter  {
    public HttpResponse createHttpResponse(ResponseData responseData) {

        ResponseData responseData1 = new ResponseData();

        Map<String, Object> originalResponse = responseData.getResponse();
        responseData1.setResponse(unfoldHeadersAndCookies(originalResponse));
        responseData1.setBody(responseData.getBody());

        HttpResponse httpResponse = WithObjectMapper.OBJECT_MAPPER.convertValue(responseData1, HttpResponse.class);
        return httpResponse;
    }

    private Map<String, Object> unfoldHeadersAndCookies(Map<String, Object> originalResponse) {
        Map<String, Object> result = new HashMap<>(originalResponse);

        String headersName = "headers";
        unfoldArrayOfTuplesToMap(result, headersName);


        return result;
    }

    private void unfoldArrayOfTuplesToMap(Map<String, Object> result, String field) {
        Object headers = result.get(field);

        if (headers instanceof Map[]) {
            result.put(field, unfold((Map[]) headers));
        }
    }

    private Map<String, Object> unfold(Map[] field) {
        Map<String, Object> result = new HashMap<>();

        for (Map map : field) {
            result.put((String) map.get("name"), map.get("values"));
        }
        return result;
    }
}
