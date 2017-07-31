package com.example.demo.mock.server.storage;

import com.example.demo.mock.server.domain.RequestCriteria;
import com.example.demo.mock.server.domain.RequestData;
import com.example.demo.mock.server.domain.ResponseData;
import com.example.demo.mock.server.service.search.SearchByCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by OTARANOVSKYI on 26.07.2017.
 */
@Component
public class RecordingStorage {

    @Autowired
    private SearchByCriteriaService searchByCriteriaService;

    private Map<RequestData, ResponseData> storage = new ConcurrentHashMap<>();

    public void saveRequestAndResponse(RequestData requestData, ResponseData responseData) {
        storage.put(requestData, responseData);
    }

    public void reset(){
        storage.clear();
    }

    public ResponseData findByCriteria(RequestCriteria requestCriteria) {
        return searchByCriteriaService.find(storage, requestCriteria);
    }
}
