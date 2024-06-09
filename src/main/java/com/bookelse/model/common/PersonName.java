package com.bookelse.model.common;

import java.util.Objects;

public class PersonName {
  private final String firstName;
  private final String lastName;

  public PersonName(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFullName() {
    return (getFirstName() + " " + getLastName()).trim();
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonName that = (PersonName) o;
    return Objects.equals(getFirstName(), that.getFirstName())
        && Objects.equals(getLastName(), that.getLastName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFirstName(), getLastName());
  }
}
