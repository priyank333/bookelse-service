package com.bookelse.model.product;

import com.bookelse.util.json.JSONUtil;
import java.util.HashMap;

public class ProductFeatures extends HashMap<String, String> {

  public static ProductFeatures jsonToObject(String jsonStr) {
    return JSONUtil.jsonToObject(jsonStr, ProductFeatures.class);
  }

  public void addFeature(String key, String value) {
    super.put(key.trim().toLowerCase(), value.trim());
  }

  public String toJSON() {
    return JSONUtil.objectToJson(this);
  }
}
