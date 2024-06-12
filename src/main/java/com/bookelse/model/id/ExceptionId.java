package com.bookelse.model.id;

public class ExceptionId extends SequenceId {

  private static final String ID_PREFIX = "AUD-EX";
  private static final String SEPARATOR = "-";

  @Override
  protected String generateId() {
    return ID_PREFIX + SEPARATOR + super.generateId();
  }
}
