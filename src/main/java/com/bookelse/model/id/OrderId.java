package com.bookelse.model.id;

public class OrderId extends SequenceId {
  private static final String ID_PREFIX = "ORD";
  private static final String SEPARATOR = "-";

  @Override
  protected String generateId() {
    return ID_PREFIX + SEPARATOR + super.generateId();
  }
}
