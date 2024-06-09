package com.bookelse.model.vendor;

import com.bookelse.model.common.Contact;
import com.bookelse.model.common.EmailId;
import com.bookelse.model.common.PersonName;
import com.bookelse.model.id.VendorId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Vendor {
  private final VendorId vendorId;
  private final PersonName name;
  private final Contact contact;
  private final EmailId emailId;
  private final Boolean isActive;
  private final ZonedDateTime createdOn;
  private final ZonedDateTime updatedOn;

  public Vendor(
      VendorId vendorId,
      PersonName name,
      Contact contact,
      EmailId emailId,
      Boolean isActive,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn) {
    this.vendorId = vendorId;
    this.name = name;
    this.contact = contact;
    this.emailId = emailId;
    this.isActive = isActive;
    this.createdOn = createdOn;
    this.updatedOn = updatedOn;
  }

  public VendorId getVendorId() {
    return vendorId;
  }

  public PersonName getName() {
    return name;
  }

  public Contact getContact() {
    return contact;
  }

  public EmailId getEmailId() {
    return emailId;
  }

  public Boolean getActive() {
    return isActive;
  }

  public ZonedDateTime getCreatedOn() {
    return createdOn;
  }

  public ZonedDateTime getUpdatedOn() {
    return updatedOn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vendor that = (Vendor) o;
    return Objects.equals(getVendorId(), that.getVendorId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getVendorId());
  }
}
