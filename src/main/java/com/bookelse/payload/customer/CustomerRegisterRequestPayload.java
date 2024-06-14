package com.bookelse.payload.customer;

import java.util.Objects;
import java.util.StringJoiner;

public record CustomerRegisterRequestPayload(
    String firstName,
    String lastName,
    String emailId,
    String password,
    String countryCode,
    String contact) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerRegisterRequestPayload that = (CustomerRegisterRequestPayload) o;
    return Objects.equals(emailId, that.emailId)
        && Objects.equals(contact, that.contact)
        && Objects.equals(lastName, that.lastName)
        && Objects.equals(password, that.password)
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(countryCode, that.countryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, emailId, password, countryCode, contact);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CustomerRegisterRequestPayload.class.getSimpleName() + "[", "]")
        .add("contact='" + contact + "'")
        .add("firstName='" + firstName + "'")
        .add("lastName='" + lastName + "'")
        .add("emailId='" + emailId + "'")
        .add("password='" + password + "'")
        .add("countryCode='" + countryCode + "'")
        .toString();
  }
}
