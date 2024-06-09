package com.bookelse.util.datetime;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public enum DateTimeFormat {
  YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
  YYYY_MM_DD_HH_MM_SS_Z("yyyy-MM-dd HH:mm:ss z"),
  YYYY_MM_DD_T_HH_MM_SS_SSSSS_Z("yyyy-MM-dd'T'HH:mm:ss.SSSSS'Z'");
  private final String dateFormatInString;
  private final DateTimeFormatter dateTimeFormatter;

  DateTimeFormat(String dateFormatInString) {
    this.dateFormatInString = dateFormatInString;
    this.dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormatInString);
  }

  public String getDateFormatInString() {
    return dateFormatInString;
  }

  public DateTimeFormatter getDateTimeFormatter() {
    return dateTimeFormatter;
  }

  public ZonedDateTime format(ZonedDateTime zonedDateTime) {
    return ZonedDateTime.parse(formatInString(zonedDateTime), dateTimeFormatter);
  }

  public String formatInString(ZonedDateTime zonedDateTime) {
    return zonedDateTime.format(dateTimeFormatter);
  }
}
