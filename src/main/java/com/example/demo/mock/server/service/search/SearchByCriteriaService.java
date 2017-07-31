package com.example.demo.mock.server.service.search;

import com.example.demo.mock.server.domain.RequestCriteria;
import com.example.demo.mock.server.domain.RequestData;
import com.example.demo.mock.server.domain.ResponseData;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class SearchByCriteriaService {
    public ResponseData find(Map<RequestData, ResponseData> storage, RequestCriteria requestCriteria) {

        for (Map.Entry<RequestData, ResponseData> entry : storage.entrySet()) {
            if (requestCriteria.satisfiedBy(entry.getKey())) {
                return entry.getValue();
            }
        }

        return null;
    }
}
