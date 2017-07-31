package com.example.demo.mock.server.service.recording.handlers;

import com.example.demo.mock.server.converter.log.LogExtractor;
import com.example.demo.mock.server.converter.log.ResponseDataProducer;
import com.example.demo.mock.server.domain.RequestData;
import com.example.demo.mock.server.domain.ResponseData;
import com.example.demo.mock.server.service.context.ContextUtils;
import com.example.demo.mock.server.service.recording.EventHandler;
import com.example.demo.mock.server.storage.InputTemporalStorage;
import com.example.demo.mock.server.storage.RecordingStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class OutputEventHandler extends EventHandler {

    @Autowired
    private InputTemporalStorage inputTemporalStorage;
    @Autowired
    private ResponseDataProducer responseDataProducer;
    @Autowired
    private RecordingStorage recordingStorage;
    @Autowired
    private ContextUtils contextUtils;
    @Autowired
    private LogExtractor logExtractor;

    @Override
    public void handle(Map<String, Object> logEvent) {
        String message = getFormattedMessage(logEvent);

        Map<String, Object> response = logExtractor.getResponse(message);

        ResponseData responseData = responseDataProducer.createResponse(response);

        String uuid = contextUtils.getFromContext(SESSION_KEY);

        RequestData requestData = inputTemporalStorage.get(uuid);

        recordingStorage.saveRequestAndResponse(requestData, responseData);

        inputTemporalStorage.remove(uuid);
    }

}
