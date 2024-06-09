package com.bookelse.payload.customer;

import java.util.Objects;

public record CustomerPostalAddressUpdateRequestPayload(
    String customerId,
    String address1,
    String address2,
    String city,
    String state,
    String country,
    String postalCode) {

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerPostalAddressUpdateRequestPayload that = (CustomerPostalAddressUpdateRequestPayload) o;
    return Objects.equals(customerId(), that.customerId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(customerId());
  }
}
