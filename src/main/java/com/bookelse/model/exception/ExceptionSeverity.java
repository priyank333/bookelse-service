package com.bookelse.model.exception;

public enum ExceptionSeverity {
  LOW("Low"),
  MEDIUM("Medium"),
  HIGH("High");

  private String description;

  ExceptionSeverity(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "ExceptionSeverity{" + "description='" + description + '\'' + '}';
  }
}
