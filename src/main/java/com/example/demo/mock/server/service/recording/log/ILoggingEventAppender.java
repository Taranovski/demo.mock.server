package com.example.demo.mock.server.service.recording.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.LogbackException;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.Status;
import com.example.demo.mock.server.util.WithObjectMapper;
import com.example.demo.mock.server.service.recording.EventHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
public class ILoggingEventAppender implements Appender<ILoggingEvent> {

    private final EventHandler eventHandler;

    public ILoggingEventAppender(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void start() {

    }

    public void stop() {

    }

    public boolean isStarted() {
        return false;
    }

    public void addFilter(Filter<ILoggingEvent> filter) {

    }

    public void clearAllFilters() {

    }

    public List<Filter<ILoggingEvent>> getCopyOfAttachedFiltersList() {
        return null;
    }

    public FilterReply getFilterChainDecision(ILoggingEvent iLoggingEvent) {
        return null;
    }

    public void setContext(Context context) {

    }

    public Context getContext() {
        return null;
    }

    public void addStatus(Status status) {

    }

    public void addInfo(String s) {

    }

    public void addInfo(String s, Throwable throwable) {

    }

    public void addWarn(String s) {

    }

    public void addWarn(String s, Throwable throwable) {

    }

    public void addError(String s) {

    }

    public void addError(String s, Throwable throwable) {

    }

    public String getName() {
        return null;
    }

    public void doAppend(ILoggingEvent iLoggingEvent) throws LogbackException {
        Map<String, Object> convertValue = WithObjectMapper.OBJECT_MAPPER.convertValue(iLoggingEvent, Map.class);

        eventHandler.handle(convertValue);
    }

    public void setName(String s) {

    }
}
