package com.bookelse.util.json;

import com.google.gson.Gson;

public final class JSONUtil {
  private static final Gson GSON;

  static {
    GSON = new Gson();
  }

  public static <T> String objectToJson(T object) {
    return GSON.toJson(object, object.getClass());
  }

  public static <T> T jsonToObject(String jsonStr, Class<T> classType) {
    return GSON.fromJson(jsonStr, classType);
  }
}
