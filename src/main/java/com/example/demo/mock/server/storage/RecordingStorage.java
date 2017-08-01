package com.example.demo.mock.server.storage;

import org.apache.commons.lang3.tuple.Pair;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by OTARANOVSKYI on 26.07.2017.
 */
@Component
public class RecordingStorage {

    private static Map<HttpRequest, Pair<HttpResponse, Long>> storage = new ConcurrentHashMap<>();

    private static volatile boolean initialized = false;

    @PostConstruct
    public void init() {
        initialized = true;
    }

    public static void save(HttpRequest requestData, HttpResponse responseData, long delay) {
        if (initialized) {
            storage.put(requestData, Pair.of(responseData, delay));
        }
    }

    public void saveRequestAndResponse(HttpRequest requestData, HttpResponse responseData, long delay) {
        storage.put(requestData, Pair.of(responseData, delay));
    }

    public void reset() {
        storage.clear();
    }

    public Map<HttpRequest, Pair<HttpResponse, Long>> getStorage() {
        return storage;
    }
}
