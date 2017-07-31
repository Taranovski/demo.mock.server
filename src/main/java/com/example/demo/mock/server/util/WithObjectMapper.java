package com.example.demo.mock.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.mockserver.client.serialization.ObjectMapperFactory;
import org.mockserver.client.serialization.serializers.string.NottableStringSerializer;
import org.mockserver.model.NottableString;

import java.io.IOException;

/**
 * Created by OTARANOVSKYI on 31.07.2017.
 */
public class WithObjectMapper {
    public static ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.createObjectMapper();

    static {
        OBJECT_MAPPER.registerModule(new MapKeySerDeModule());
    }

    private static class MapKeySerDeModule extends SimpleModule {
        MapKeySerDeModule() {
            addKeyDeserializer(NottableString.class, new KeyDeserializer() {
                @Override
                public Object deserializeKey(String s, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                    return NottableString.string(s);
                }
            });

            addKeySerializer(NottableString.class, new NottableStringSerializer());

        }
    }
}
