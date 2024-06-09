package com.bookelse.payload.vendor;

import java.util.Objects;

public record NewVendorRequestPayload(
    String firstName,
    String lastName,
    String countryCode,
    String contact,
    String emailId,
    Boolean isActive) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NewVendorRequestPayload that = (NewVendorRequestPayload) o;
    return Objects.equals(contact, that.contact)
        && Objects.equals(emailId, that.emailId)
        && Objects.equals(lastName, that.lastName)
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(isActive, that.isActive)
        && Objects.equals(countryCode, that.countryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, countryCode, contact, emailId, isActive);
  }
}
