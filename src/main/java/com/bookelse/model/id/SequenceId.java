package com.bookelse.model.id;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class SequenceId extends Id {
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssSSS");
  private static final AtomicInteger SEQUENCE = new AtomicInteger(0);
  private static final int MAX_SEQUENCE = 999;
  private static long lastTimestamp = -1L;

  public SequenceId(String id) {
    super(id);
  }

  public SequenceId() {}

  @Override
  protected String generateId() {
    long currentTimestamp = System.currentTimeMillis();
    if (currentTimestamp != lastTimestamp) {
      lastTimestamp = currentTimestamp;
      SEQUENCE.set(0);
    }
    if (SEQUENCE.get() > MAX_SEQUENCE) {
      while (currentTimestamp == System.currentTimeMillis()) {}
      currentTimestamp = System.currentTimeMillis();
      lastTimestamp = currentTimestamp;
      SEQUENCE.set(0);
    }
    String timestamp = DATE_FORMAT.format(new Date(currentTimestamp));
    int sequence = SEQUENCE.getAndIncrement();
    return timestamp + String.format("%03d", sequence);
  }
}
