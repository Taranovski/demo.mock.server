package com.example.demo.mock.server.service.filtering;

import com.example.demo.mock.server.domain.StoredRecord;
import com.example.demo.mock.server.repository.StorageQualifiers;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
@Component
@Qualifier(StorageQualifiers.IN_MEMORY)
public class EventCriteriaProvider implements CriteriaProvider<StoredRecord> {

    @Override
    public Predicate<StoredRecord> forRequest(HttpRequest httpRequest) {
        return record -> new EqualsBuilder()
                .append(httpRequest.getMethod(), record.getRequest().getMethod())
                .append(httpRequest.getPath(), record.getRequest().getPath())
                .append(httpRequest.getBody(), record.getRequest().getBody())
                .append(httpRequest.isKeepAlive(), record.getRequest().isKeepAlive())
                .append(httpRequest.isSecure(), record.getRequest().isSecure())
                .append(httpRequest.getFirstHeader("Accept-Encoding"), record.getRequest().getFirstHeader("Accept-Encoding"))
                .append(httpRequest.getFirstHeader("Accept"), record.getRequest().getFirstHeader("Accept"))
                .append(httpRequest.getFirstHeader("SOAPAction"), record.getRequest().getFirstHeader("SOAPAction"))
                .append(httpRequest.getFirstHeader("Host"), record.getRequest().getFirstHeader("Host"))
                .append(httpRequest.getFirstHeader("Content-Length"), record.getRequest().getFirstHeader("Content-Length"))
                .isEquals();
    }
}
