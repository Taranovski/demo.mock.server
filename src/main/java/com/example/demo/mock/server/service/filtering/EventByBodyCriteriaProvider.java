package com.example.demo.mock.server.service.filtering;

import com.example.demo.mock.server.domain.StoredRecord;
import com.example.demo.mock.server.repository.StorageQualifiers;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.mockserver.model.Body;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
@Qualifier(StorageQualifiers.FS_STORED)
@Order(3)
public class EventByBodyCriteriaProvider implements CriteriaProvider<StoredRecord> {

    public Predicate<StoredRecord> forRequest(HttpRequest httpRequest) {
        return record -> new EqualsBuilder()
                .append(Optional.ofNullable(httpRequest)
                        .map(HttpRequest::getBody)
                        .map(Body::getValue)
                        .orElse(null), record.getRequest().getBody().getValue())
                .isEquals();
    }
}
