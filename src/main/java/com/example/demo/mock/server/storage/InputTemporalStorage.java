package com.example.demo.mock.server.storage;

import com.example.demo.mock.server.domain.RequestData;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class InputTemporalStorage {

    private Map<String, RequestData> storage = new ConcurrentHashMap<>();

    public void put(String uuid, RequestData requestData) {
        storage.put(uuid, requestData);
    }

    public RequestData get(String uuid) {
        return storage.get(uuid);
    }

    public void remove(String uuid) {
        storage.remove(uuid);
    }
}
