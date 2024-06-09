package com.bookelse.model.id;

public class VendorId extends SequenceId {
  private static final String ID_PREFIX = "VND";
  private static final String SEPARATOR = "-";

  public VendorId(String vendorId) {
    super(vendorId);
  }

  public VendorId() {}

  @Override
  protected String generateId() {
    return ID_PREFIX + SEPARATOR + super.generateId();
  }
}
