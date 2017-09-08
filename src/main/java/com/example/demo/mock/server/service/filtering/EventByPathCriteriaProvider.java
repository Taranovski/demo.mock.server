package com.example.demo.mock.server.service.filtering;

import com.example.demo.mock.server.domain.StoredRecord;
import com.example.demo.mock.server.repository.StorageQualifiers;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@Qualifier(StorageQualifiers.FS_STORED)
@Order(1)
public class EventByPathCriteriaProvider implements CriteriaProvider<StoredRecord> {

    public Predicate<StoredRecord> forRequest(HttpRequest httpRequest) {
        return record -> new EqualsBuilder()
                .append(httpRequest.getPath(), record.getRequest().getPath())
                .isEquals();
    }
}
