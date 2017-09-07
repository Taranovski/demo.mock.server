package com.example.demo.mock.server.repository;

import com.example.demo.mock.server.service.filtering.CriteriaProvider;
import com.example.demo.mock.server.service.search.SearchByCriteriaService;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
@Component
public class ResponseRepository {

    @Autowired
    private SearchByCriteriaService searchByCriteriaService;
    @Autowired
    @Qualifier(StorageQualifiers.FS_STORED)
    private CriteriaProvider criteriaProvider;

    public HttpResponse findResponseByCriteria(HttpRequest request) {
        return searchByCriteriaService.find(criteriaProvider.forRequest(request));
    }

}
