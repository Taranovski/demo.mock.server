package com.example.demo.mock.server.storage;

import com.example.demo.mock.server.converter.HttpResponseProducer;
import com.example.demo.mock.server.domain.RequestCriteria;
import com.example.demo.mock.server.domain.ResponseData;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
@Component
public class ResponseRepository {

    @Autowired
    private HttpResponseProducer httpResponseProducer;

    @Autowired
    private RecordingStorage recordingStorage;

    public HttpResponse findResponseByCriteria(RequestCriteria requestCriteria) {
        ResponseData responseData = recordingStorage.findByCriteria(requestCriteria);

        return httpResponseProducer.createHttpResponse(responseData);
    }

}
