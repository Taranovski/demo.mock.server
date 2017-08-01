package com.example.demo.mock.server.util.context;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@Component
public class UuidGenerator {
    public String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
