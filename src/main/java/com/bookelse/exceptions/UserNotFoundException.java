package com.bookelse.exceptions;

import com.bookelse.model.exception.AuditableExceptionWrapper;
import com.bookelse.model.exception.ExceptionSeverity;
import com.bookelse.model.id.ExceptionId;

public class UserNotFoundException extends RuntimeExceptionAuditable {
  private static final String MESSAGE = "User Not Found";

  public UserNotFoundException(String userId) {
    super(MESSAGE + " : " + userId);
  }

  @Override
  public UserNotFoundException wrapAuditableException(
      String userId, ExceptionSeverity exceptionSeverity) {
    auditableExceptionWrapper =
        new AuditableExceptionWrapper<>(new ExceptionId(), this, userId, exceptionSeverity);
    return this;
  }
}
