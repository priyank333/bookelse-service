package com.bookelse.model.exception;

import com.bookelse.config.ApplicationContextConfiguration;
import com.bookelse.dao.ExceptionAuditDAO;
import com.bookelse.model.exception.request.ServletWebRequest;
import com.bookelse.model.id.ExceptionId;
import com.bookelse.util.json.JSONUtil;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class AuditableExceptionWrapper<T extends Throwable> {
  private final ExceptionId exceptionId;
  private final String userId;
  private final LocalDateTime timestamp;
  private final String exceptionType;
  private final String stackTrace;
  private final String detailedMessage;
  private final String environment;
  private final ExceptionSeverity severity;
  private final String correlationId;
  private final Map<String, Object> additionalData;
  private ServletWebRequest webRequest;

  public AuditableExceptionWrapper(
      ExceptionId exceptionId, T throwable, String userId, ExceptionSeverity exceptionSeverity) {
    this.exceptionId = exceptionId;
    this.userId = userId;
    this.timestamp = LocalDateTime.now();
    this.exceptionType = throwable.getClass().getSimpleName();
    this.stackTrace = Arrays.toString(throwable.getStackTrace());
    this.detailedMessage = throwable.toString();
    this.environment = "DEV";
    this.severity = exceptionSeverity;
    this.correlationId = "";
    this.additionalData = new HashMap<>();
  }

  public ExceptionId getExceptionId() {
    return exceptionId;
  }

  public String getUserId() {
    return userId;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getExceptionType() {
    return exceptionType;
  }

  public String getStackTrace() {
    return stackTrace;
  }

  public String getEnvironment() {
    return environment;
  }

  public ExceptionSeverity getSeverity() {
    return severity;
  }

  public String getCorrelationId() {
    return correlationId;
  }

  public String getDetailedMessage() {
    return detailedMessage;
  }

  public Map<String, Object> getAdditionalData() {
    return additionalData;
  }

  public AuditableExceptionWrapper<T> addAdditionalData(String key, Object value) {
    additionalData.put(key, value);
    return this;
  }

  public void captureServletWebRequest(
      org.springframework.web.context.request.ServletWebRequest servletWebRequest) {
    this.webRequest = new ServletWebRequest(servletWebRequest);
  }

  public ServletWebRequest getWebRequest() {
    return webRequest;
  }

  public String getWebRequestAsJsonString() {
    if (Objects.isNull(getWebRequest())) {
      return "{}";
    }
    return JSONUtil.objectToJson(getWebRequest());
  }

  public String getAdditionalDataAsJsonString() {
    if (Objects.isNull(getAdditionalData())) {
      return "{}";
    }
    return JSONUtil.objectToJson(getAdditionalData());
  }

  public ServletWebRequest getServletWebRequest(String jsonString) {
    return JSONUtil.jsonToObject(jsonString, ServletWebRequest.class);
  }

  public Map<String, Object> getAdditionalData(String json) {
    return JSONUtil.jsonToObject(json, Map.class);
  }

  public boolean auditInDB() {
    AuditableExceptionWrapper<T> insertedObj = null;
    ExceptionAuditDAO exceptionAuditDAO =
        ApplicationContextConfiguration.getApplicationContext().getBean(ExceptionAuditDAO.class);
    try {
      insertedObj = exceptionAuditDAO.addExceptionAudit(this);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return !Objects.isNull(insertedObj);
  }
}
