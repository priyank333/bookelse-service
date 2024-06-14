package com.bookelse.util.datetime;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;

public final class DateTimeUtility {
  private DateTimeUtility() {}

  public static ZonedDateTime getCurrentUTCDateTime() {
    return ZonedDateTime.now(ZoneOffset.UTC);
  }

  public static Instant zonedDateTimeToInstant(ZonedDateTime zonedDateTime) {
    Objects.requireNonNull(zonedDateTime);
    return zonedDateTime.toInstant();
  }

  public static Timestamp instantToTimestamp(Instant instant) {
    Objects.requireNonNull(instant);
    return Timestamp.from(instant);
  }

  public static Timestamp zonedDateTimeToTimestamp(ZonedDateTime zonedDateTime) {
    Objects.requireNonNull(zonedDateTime);
    return instantToTimestamp(zonedDateTimeToInstant(zonedDateTime));
  }

  public static ZonedDateTime timestampToZonedDateTime(Timestamp timestamp, ZoneId zoneId) {
    Objects.requireNonNull(timestamp, "Timestamp object is required");
    Objects.requireNonNull(zoneId, "ZoneId object is required");
    return ZonedDateTime.ofInstant(timestamp.toInstant(), zoneId);
  }
}
