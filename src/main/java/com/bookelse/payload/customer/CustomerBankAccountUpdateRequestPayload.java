package com.bookelse.payload.customer;

import java.util.Objects;

public record CustomerBankAccountUpdateRequestPayload(
    String customerId, String bankAccountName, String bankAccountNo, String bankIfscCode) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerBankAccountUpdateRequestPayload that = (CustomerBankAccountUpdateRequestPayload) o;
    return Objects.equals(customerId, that.customerId)
        && Objects.equals(bankIfscCode, that.bankIfscCode)
        && Objects.equals(bankAccountNo, that.bankAccountNo)
        && Objects.equals(bankAccountName, that.bankAccountName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, bankAccountName, bankAccountNo, bankIfscCode);
  }
}
