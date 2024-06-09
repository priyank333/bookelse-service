package com.bookelse.payload.customer;

import com.bookelse.util.datetime.ZoneId;
import java.util.Objects;

public record CustomerRegisterRequestPayload(
    String firstName,
    String lastName,
    String emailId,
    String password,
    String countryCode,
    String contact,
    ZoneId zoneId) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerRegisterRequestPayload that = (CustomerRegisterRequestPayload) o;
    return zoneId == that.zoneId
        && Objects.equals(emailId, that.emailId)
        && Objects.equals(contact, that.contact)
        && Objects.equals(lastName, that.lastName)
        && Objects.equals(password, that.password)
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(countryCode, that.countryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, emailId, password, countryCode, contact, zoneId);
  }
}
