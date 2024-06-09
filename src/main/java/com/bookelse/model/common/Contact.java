package com.bookelse.model.common;

import java.util.StringJoiner;

public class Contact {
  private final String countryCode;
  private final String contact;

  public Contact(String countryCode, String contact) {
    this.countryCode = countryCode;
    this.contact = contact;
  }

  public Contact(String completeContact) {
    String[] splitVal = completeContact.split(" ");
    this.countryCode = splitVal[0];
    this.contact = splitVal[1];
  }

  public String getCountryCode() {
    return countryCode;
  }

  public String getContact() {
    return contact;
  }

  public String getCompleteContact() {
    return (getCountryCode() + " " + getContact()).trim();
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Contact.class.getSimpleName() + "[", "]")
        .add("countryCode='" + countryCode + "'")
        .add("contact='" + contact + "'")
        .toString();
  }
}
