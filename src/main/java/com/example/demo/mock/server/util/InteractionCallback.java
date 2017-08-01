package com.example.demo.mock.server.util;

import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

/**
 * Created by OTARANOVSKYI on 01.08.2017.
 */
public interface InteractionCallback {
    void call(HttpRequest httpRequest, HttpResponse httpResponse, Long delay);
}
