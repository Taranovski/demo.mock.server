package com.example.demo.mock.server.service.recording;

import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
public abstract class EventHandler {
    protected final String SESSION_KEY = "currentSessionKey";

    public abstract void handle(Map<String, Object> logEvent);

    protected final String getFormattedMessage(Map<String, Object> logEvent) {
        return (String) logEvent.get("formattedMessage");
    }
}
