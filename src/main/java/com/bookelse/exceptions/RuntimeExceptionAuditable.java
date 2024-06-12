package com.bookelse.exceptions;

import com.bookelse.model.exception.AuditableExceptionWrapper;
import com.bookelse.model.exception.ExceptionSeverity;
import com.bookelse.model.id.ExceptionId;

public class RuntimeExceptionAuditable extends RuntimeException implements AuditableException {
  protected AuditableExceptionWrapper<?> auditableExceptionWrapper;

  public RuntimeExceptionAuditable(String message) {
    super(new RuntimeException(message));
  }

  public RuntimeExceptionAuditable(Throwable cause) {
    super(cause);
  }

  @Override
  public RuntimeException wrapAuditableException(
      String userId, ExceptionSeverity exceptionSeverity) {
    this.auditableExceptionWrapper =
        new AuditableExceptionWrapper<>(
            new ExceptionId(), super.getCause(), userId, exceptionSeverity);
    return this;
  }

  public AuditableExceptionWrapper<?> getAuditableExceptionWrapper() {
    return auditableExceptionWrapper;
  }
}
