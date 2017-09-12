package com.example.demo.mock.server.service.filtering;

import com.example.demo.mock.server.domain.StoredRecord;
import org.mockserver.model.HttpRequest;

import java.util.function.Predicate;

@FunctionalInterface
public interface CriteriaProvider<T extends StoredRecord> {
    Predicate<T> forRequest(HttpRequest httpRequest);
}
