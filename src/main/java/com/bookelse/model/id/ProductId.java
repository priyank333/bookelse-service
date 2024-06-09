package com.bookelse.model.id;

public class ProductId extends SequenceId {
  private static final String ID_PREFIX = "PRD";
  private static final String SEPARATOR = "-";

  public ProductId(String id) {
    super(id);
  }

  public ProductId() {}

  @Override
  protected String generateId() {
    return ID_PREFIX + SEPARATOR + super.generateId();
  }
}
