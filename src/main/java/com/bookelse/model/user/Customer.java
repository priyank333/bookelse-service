package com.bookelse.model.user;

import com.bookelse.model.common.*;
import com.bookelse.model.id.CustomerId;
import com.bookelse.model.id.Id;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Customer extends User {
  private final PersonName name;
  private final EmailId emailId;
  private final Contact contact;
  private Address address;
  private BankAccount bankAccount;

  public Customer(
      Id userId,
      Password password,
      PersonName name,
      EmailId emailId,
      Contact contact,
      Address address,
      BankAccount bankAccount,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn) {
    super(userId, password, createdOn, updatedOn);
    this.name = name;
    this.emailId = emailId;
    this.contact = contact;
    this.address = address;
    this.bankAccount = bankAccount;
  }

  public Customer(
      Id userId,
      Password password,
      PersonName name,
      EmailId emailId,
      Contact contact,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn) {
    super(userId, password, createdOn, updatedOn);
    this.name = name;
    this.emailId = emailId;
    this.contact = contact;
  }

  public Customer(
      Password password,
      PersonName name,
      EmailId emailId,
      Contact contact,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn) {
    super(new CustomerId(), password, createdOn, updatedOn);
    this.name = name;
    this.emailId = emailId;
    this.contact = contact;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getEmailId());
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    if (!super.equals(object)) return false;
    Customer customer = (Customer) object;
    return Objects.equals(getEmailId(), customer.getEmailId());
  }

  public PersonName getName() {
    return name;
  }

  public EmailId getEmailId() {
    return emailId;
  }

  public Contact getContact() {
    return contact;
  }

  public Address getAddress() {
    return address;
  }

  public BankAccount getBankAccount() {
    return bankAccount;
  }
}
