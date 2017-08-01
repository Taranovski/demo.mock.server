package com.example.demo.mock.server.util.callback;

import com.example.demo.mock.server.storage.RecordingStorage;
import com.example.demo.mock.server.util.InteractionCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

/**
 * Created by OTARANOVSKYI on 01.08.2017.
 */
public class StaticStoringInteractionCallback implements InteractionCallback {

    @Override
    public void call(HttpRequest httpRequest, HttpResponse httpResponse, Long delay) {
        RecordingStorage.save(httpRequest, httpResponse, delay);
    }
}
