package com.bookelse.exceptions.handler;

import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.exceptions.UserNotFoundException;
import com.bookelse.model.exception.AuditableExceptionWrapper;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(value = UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public @ResponseBody ErrorResponse handleUserNotFoundException(
      UserNotFoundException e, ServletWebRequest webRequest) {
    AuditableExceptionWrapper<UserNotFoundException> auditableExceptionWrapper =
        (AuditableExceptionWrapper<UserNotFoundException>) e.getAuditableExceptionWrapper();
    auditableExceptionWrapper.captureServletWebRequest(webRequest);
    ErrorResponse errorResponse =
        ErrorResponse.builder(e, ProblemDetail.forStatus(HttpStatus.NOT_FOUND)).detail("").build();
    auditableExceptionWrapper.auditInDB();
    return errorResponse;
  }

  @ExceptionHandler(value = DataAccessException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody ErrorResponse handleDataAccessException(
      RuntimeExceptionAuditable e, ServletWebRequest webRequest) {

    AuditableExceptionWrapper<DataAccessException> auditableExceptionWrapper =
        (AuditableExceptionWrapper<DataAccessException>) e.getAuditableExceptionWrapper();
    ErrorResponse errorResponse =
        ErrorResponse.builder(e, ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR))
            .detail("")
            .build();
    auditableExceptionWrapper.captureServletWebRequest(webRequest);
    auditableExceptionWrapper.auditInDB();
    return errorResponse;
  }

  @ExceptionHandler(value = RuntimeExceptionAuditable.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody ErrorResponse handleRuntimeException(
      RuntimeExceptionAuditable e, ServletWebRequest webRequest) {
    AuditableExceptionWrapper<RuntimeExceptionAuditable> auditableExceptionWrapper =
        (AuditableExceptionWrapper<RuntimeExceptionAuditable>) e.getAuditableExceptionWrapper();
    ErrorResponse errorResponse =
        ErrorResponse.builder(e, ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR))
            .detail("")
            .build();
    auditableExceptionWrapper.captureServletWebRequest(webRequest);
    auditableExceptionWrapper.auditInDB();
    return errorResponse;
  }

  @ExceptionHandler(value = InvalidKeySpecException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody ErrorResponse handleInvalidKeySpecException(RuntimeExceptionAuditable e) {
    AuditableExceptionWrapper<InvalidKeySpecException> auditableExceptionWrapper =
        (AuditableExceptionWrapper<InvalidKeySpecException>) e.getAuditableExceptionWrapper();
    ErrorResponse errorResponse =
        ErrorResponse.builder(e, ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR))
            .detail("")
            .build();
    auditableExceptionWrapper.auditInDB();
    return errorResponse;
  }

  @ExceptionHandler(value = NoSuchAlgorithmException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody ErrorResponse handleNoSuchAlgorithmException(RuntimeExceptionAuditable e) {
    AuditableExceptionWrapper<NoSuchAlgorithmException> auditableExceptionWrapper =
        (AuditableExceptionWrapper<NoSuchAlgorithmException>) e.getAuditableExceptionWrapper();
    ErrorResponse errorResponse =
        ErrorResponse.builder(e, ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR))
            .detail("")
            .build();
    auditableExceptionWrapper.auditInDB();
    return errorResponse;
  }
}
