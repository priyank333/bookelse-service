package com.bookelse.util.datetime;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;

public final class DateTimeUtility {
  private DateTimeUtility() {}

  public static ZonedDateTime utcTimezoneConvert(ZonedDateTime dateTime, ZoneId zoneId) {
    if (dateTime == null || zoneId == null) return null;
    if (dateTime.getZone().equals(zoneId.getZoneId())) {
      return dateTime;
    } else {
      return dateTime.withZoneSameInstant(zoneId.getZoneId());
    }
  }

  public static ZonedDateTime getCurrentUTCDateTime() {
    return ZonedDateTime.now(ZoneOffset.UTC);
  }

  public static ZonedDateTime instantToZonedDateTime(Instant instant, ZoneId zoneId) {
    Objects.requireNonNull(instant, "Instant object is required");
    Objects.requireNonNull(zoneId, "ZoneId object is required");
    return ZonedDateTime.ofInstant(instant, java.time.ZoneId.of(zoneId.getZoneIdName()));
  }

  public static Instant timestampToInstant(Timestamp timestamp) throws NullPointerException {
    Objects.requireNonNull(timestamp, "Timestamp object is required");
    return timestamp.toInstant();
  }

  public static ZonedDateTime timestampToZonedDateTime(Timestamp timestamp, ZoneId zoneId) {
    Objects.requireNonNull(timestamp, "Timestamp object is required");
    Objects.requireNonNull(zoneId, "ZoneId object is required");
    return instantToZonedDateTime(timestampToInstant(timestamp), zoneId);
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
    return instantToTimestamp(zonedDateTimeToInstant(zonedDateTime));
  }
}
