package com.example.demo.mock.server.service.search;

import com.example.demo.mock.server.domain.RequestCriteria;
import com.example.demo.mock.server.storage.RecordingStorage;
import org.apache.commons.lang3.tuple.Pair;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class SearchByCriteriaService {

    @Autowired
    private RecordingStorage recordingStorage;

    public HttpResponse find(RequestCriteria requestCriteria) {
        for (Map.Entry<HttpRequest, Pair<HttpResponse, Long>> entry : recordingStorage.getStorage().entrySet()) {
            if (requestCriteria.satisfiedBy(entry.getKey())) {
                Pair<HttpResponse, Long> value = entry.getValue();
                return value.getKey();
            }
        }

        return null;
    }
}
