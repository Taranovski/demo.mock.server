package com.example.demo.mock.server.repository;

import com.example.demo.mock.server.domain.RequestCriteria;
import com.example.demo.mock.server.service.search.SearchByCriteriaService;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
@Component
public class ResponseRepository {

    @Autowired
    private SearchByCriteriaService searchByCriteriaService;

    public HttpResponse findResponseByCriteria(RequestCriteria requestCriteria) {
        return searchByCriteriaService.find(requestCriteria);
    }

}
