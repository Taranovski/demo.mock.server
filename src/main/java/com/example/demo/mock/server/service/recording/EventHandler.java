package com.example.demo.mock.server.service.recording;

import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
public interface EventHandler {
    String SESSION_KEY = "currentSessionKey";

    void handle(Map convertValue);
}
