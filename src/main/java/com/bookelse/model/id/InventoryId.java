package com.bookelse.model.id;

public class InventoryId extends SequenceId {

  private static final String ID_PREFIX = "INV";
  private static final String SEPARATOR = "-";

  @Override
  protected String generateId() {
    return ID_PREFIX + SEPARATOR + super.generateId();
  }
}
