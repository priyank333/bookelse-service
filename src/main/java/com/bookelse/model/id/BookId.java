package com.bookelse.model.id;

public class BookId extends SequenceId {
  private static final String ID_PREFIX = "BOOK";
  private static final String SEPARATOR = "-";

  public BookId(String id) {
    super(id);
  }

  public BookId() {}

  @Override
  protected String generateId() {
    return ID_PREFIX + SEPARATOR + super.generateId();
  }
}
