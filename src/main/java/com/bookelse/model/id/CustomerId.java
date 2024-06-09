package com.bookelse.model.id;

public class CustomerId extends SequenceId {
  private static final String ID_PREFIX = "CST";
  private static final String SEPARATOR = "-";

  @Override
  protected String generateId() {
    return ID_PREFIX + SEPARATOR + super.generateId();
  }
}
