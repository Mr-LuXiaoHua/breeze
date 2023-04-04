package com.breeze.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 一枕清风
 * @date 2023/3/24
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJsonString(Object obj) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        T t = null;
        try {
            t = objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> List<T> toList(String json, Class<T> object){
        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, object);
            List<T> list = objectMapper.readValue(json, listType);
            return list;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
