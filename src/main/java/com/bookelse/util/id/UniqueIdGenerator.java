package com.bookelse.util.id;

import java.util.UUID;

public final class UniqueIdGenerator {
  public static String generateUniqueId() {
    // Generate a UUID (Universally Unique Identifier)
    UUID uuid = UUID.randomUUID();
    // Convert UUID to String and replace "-" with empty string
    return uuid.toString().replace("-", "");
  }
}
