package com.bookelse.model.id;

import java.util.UUID;

public class RandomUUID extends Id {
  public RandomUUID() {}

  public RandomUUID(String id) {
    super(id);
  }

  @Override
  protected String generateId() {
    return UUID.randomUUID().toString();
  }
}
