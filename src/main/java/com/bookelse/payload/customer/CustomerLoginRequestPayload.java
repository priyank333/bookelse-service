package com.bookelse.payload.customer;

import java.util.Objects;

public record CustomerLoginRequestPayload(String emailId, String password) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerLoginRequestPayload that = (CustomerLoginRequestPayload) o;
    return Objects.equals(emailId, that.emailId) && Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(emailId, password);
  }
}
