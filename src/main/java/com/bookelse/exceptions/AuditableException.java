package com.bookelse.exceptions;

import com.bookelse.model.exception.ExceptionSeverity;

public interface AuditableException<T extends Throwable> {
  T wrapAuditableException(String userId, ExceptionSeverity exceptionSeverity);
}
