package com.bookelse.util.datetime;

import java.util.Arrays;
import java.util.Optional;

public enum ZoneId {
  ASIA_KOLKATA("Asia/Kolkata", Boolean.TRUE),
  UTC("UTC", Boolean.TRUE);
  private final String zoneIdName;
  private final Boolean isAvailable;
  private final java.time.ZoneId zoneId;

  ZoneId(String zoneIdName, Boolean isAvailable) {
    this.zoneIdName = zoneIdName;
    this.isAvailable = isAvailable;
    this.zoneId = java.time.ZoneId.of(zoneIdName);
  }

  public static ZoneId getInstanceByZoneIdName(String zoneIdName) {
    Optional<ZoneId> zoneIdOptional =
        Arrays.stream(values())
            .filter(zoneId -> zoneId.getZoneIdName().equals(zoneIdName))
            .findAny();

    return zoneIdOptional.orElse(UTC);
  }

  public String getZoneIdName() {
    return zoneIdName;
  }

  public Boolean getAvailable() {
    return isAvailable;
  }

  public java.time.ZoneId getZoneId() {
    return zoneId;
  }
}
