package com.lightit.automated.qa.test.api.utilits.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Serialization {
    public static <T> T serializeJsonToObject(String response, Class<T> tClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, tClass);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
