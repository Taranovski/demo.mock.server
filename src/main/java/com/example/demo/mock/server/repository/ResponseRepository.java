package com.example.demo.mock.server.repository;

import com.example.demo.mock.server.domain.StoredRecord;
import com.example.demo.mock.server.service.filtering.CriteriaProvider;
import com.example.demo.mock.server.service.search.SearchByCriteriaService;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
@Component
public class ResponseRepository<R extends StoredRecord> {

    @Autowired
    private SearchByCriteriaService<R> searchByCriteriaService;
    @Autowired
    @Qualifier(StorageQualifiers.CURRENT_STORAGE_TYPE)
    private List<CriteriaProvider<R>> criteriaProvider;

    public HttpResponse findResponseByCriteria(HttpRequest request) {
        return searchByCriteriaService.find(buildCriteria(request));
    }

    private Predicate<R> buildCriteria(HttpRequest request) {
        return criteriaProvider
                    .stream()
                    .map(provider -> provider.forRequest(request))
                    .reduce(Predicate::and)
                    .orElse((r) -> true);
    }

}
