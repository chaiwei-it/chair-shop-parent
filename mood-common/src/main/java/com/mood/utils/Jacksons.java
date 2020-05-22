package com.mood.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Zhao Junjian
 */
public final class Jacksons {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Jacksons() {
    }

    public static <T> String parse(T obj) {
        try {
            if (obj == null) {
                return null;
            }
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static <T> String parseInPrettyMode(T obj) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }
}
