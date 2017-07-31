package com.example.demo.mock.server.service.recording;

import ch.qos.logback.classic.Logger;
import com.example.demo.mock.server.service.recording.log.ILoggingEventAppender;
import com.example.demo.mock.server.service.recording.handlers.InputEventHandler;
import com.example.demo.mock.server.service.recording.handlers.OutputEventHandler;
import org.mockserver.matchers.HttpRequestMatcher;
import org.mockserver.mockserver.MockServerHandler;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
@Component
public class RecordingService {

    private Logger inputLogger;
    private Logger outputLogger;
    private ILoggingEventAppender inputAppender;
    private ILoggingEventAppender outputAppender;

    @Autowired
    private InputEventHandler inputEventHandler;

    @Autowired
    private OutputEventHandler outputEventHandler;

    @PostConstruct
    public void init() {
        inputLogger = (Logger) LoggerFactory.getLogger(HttpRequestMatcher.class);
        outputLogger = (Logger) LoggerFactory.getLogger(MockServerHandler.class);
        inputAppender = new ILoggingEventAppender(inputEventHandler);
        outputAppender = new ILoggingEventAppender(outputEventHandler);
    }


    public void startRecordingOfEventsKeyAndContent() {
        inputLogger.addAppender(inputAppender);
        outputLogger.addAppender(outputAppender);
    }

    public void stopRecordingEvents() {
        inputLogger.detachAppender(inputAppender);
        outputLogger.detachAppender(outputAppender);
    }
}
