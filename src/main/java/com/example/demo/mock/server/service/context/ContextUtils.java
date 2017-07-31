package com.example.demo.mock.server.service.context;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class ContextUtils {
    ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    public void putToContext(String key, String value) {
        threadLocal.set(Collections.singletonMap(key, value));
    }

    public String getFromContext(String key) {
        String s = threadLocal.get().get(key);
        threadLocal.remove();
        return s;
    }
}
