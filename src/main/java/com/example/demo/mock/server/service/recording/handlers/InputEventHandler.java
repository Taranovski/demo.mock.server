package com.example.demo.mock.server.service.recording.handlers;

import com.example.demo.mock.server.converter.RequestDataProducer;
import com.example.demo.mock.server.domain.RequestData;
import com.example.demo.mock.server.service.context.ContextUtils;
import com.example.demo.mock.server.service.context.UuidGenerator;
import com.example.demo.mock.server.service.recording.EventHandler;
import com.example.demo.mock.server.storage.InputTemporalStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class InputEventHandler implements EventHandler {

    @Autowired
    private InputTemporalStorage inputTemporalStorage;
    @Autowired
    private RequestDataProducer requestDataProducer;
    @Autowired
    private UuidGenerator uuidGenerator;
    @Autowired
    private ContextUtils contextUtils;

    @Override
    public void handle(Map convertValue) {
        RequestData requestData = requestDataProducer.createRequestData(convertValue);

        String uuid = uuidGenerator.generateUuid();

        contextUtils.putToContext(SESSION_KEY, uuid);

        inputTemporalStorage.put(uuid, requestData);
    }

}
