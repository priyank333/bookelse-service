package com.bookelse.model.common;

import java.util.StringJoiner;

public class Address {
  private final String address1;
  private final String address2;
  private final String city;
  private final String state;
  private final String country;
  private final String postalCode;

  public Address(
      String address1,
      String address2,
      String city,
      String state,
      String country,
      String postalCode) {
    this.address1 = address1;
    this.address2 = address2;
    this.city = city;
    this.state = state;
    this.country = country;
    this.postalCode = postalCode;
  }

  public String getAddress1() {
    return address1;
  }

  public String getAddress2() {
    return address2;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCountry() {
    return country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
        .add("address1='" + address1 + "'")
        .add("address2='" + address2 + "'")
        .add("city='" + city + "'")
        .add("state='" + state + "'")
        .add("country='" + country + "'")
        .add("postalCode='" + postalCode + "'")
        .toString();
  }
}
