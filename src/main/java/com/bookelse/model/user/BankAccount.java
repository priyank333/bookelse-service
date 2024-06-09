package com.bookelse.model.user;

public class BankAccount {
  private final String bankAccountName;
  private final String bankAccountNo;
  private final String bankIfscCode;

  public BankAccount(String bankAccountName, String bankAccountNo, String bankIfscCode) {
    this.bankAccountName = bankAccountName;
    this.bankAccountNo = bankAccountNo;
    this.bankIfscCode = bankIfscCode;
  }

  public String getBankAccountName() {
    return bankAccountName;
  }

  public String getBankAccountNo() {
    return bankAccountNo;
  }

  public String getBankIfscCode() {
    return bankIfscCode;
  }
}
