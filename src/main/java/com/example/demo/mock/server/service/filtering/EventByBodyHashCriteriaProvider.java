package com.example.demo.mock.server.service.filtering;

import com.example.demo.mock.server.domain.record.LazyFSStoredRecord;
import com.example.demo.mock.server.repository.StorageQualifiers;
import com.google.common.base.Strings;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@Qualifier(StorageQualifiers.FS_STORED)
@Order(2)
public class EventByBodyHashCriteriaProvider implements CriteriaProvider<LazyFSStoredRecord> {

    public Predicate<LazyFSStoredRecord> forRequest(HttpRequest httpRequest) {
        return record -> new EqualsBuilder()
                .append(Strings.nullToEmpty(httpRequest.getBodyAsString()).hashCode(), record.getBodyHash())
                .isEquals();
    }
}
