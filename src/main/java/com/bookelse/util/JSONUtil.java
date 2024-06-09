package com.bookelse.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public final class JSONUtil {
  private static final ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
  }

  public static <T> String objectToJson(T object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T jsonToObject(String jsonStr, Class<T> classType) {
    try {
      return objectMapper.readValue(jsonStr.getBytes(), classType);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
