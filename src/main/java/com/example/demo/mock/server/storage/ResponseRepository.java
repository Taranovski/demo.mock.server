package com.example.demo.mock.server.storage;

import com.example.demo.mock.server.converter.http.ResponseDataToHttpResponseConverter;
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
    private ResponseDataToHttpResponseConverter responseDataToHttpResponseConverter;

    @Autowired
    private RecordingStorage recordingStorage;

    public HttpResponse findResponseByCriteria(RequestCriteria requestCriteria) {
        ResponseData responseData = recordingStorage.findByCriteria(requestCriteria);

        return responseDataToHttpResponseConverter.createHttpResponse(responseData);
    }

}
