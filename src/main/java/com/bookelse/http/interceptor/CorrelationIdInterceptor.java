package com.bookelse.http.interceptor;

import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.exception.ExceptionSeverity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("CorrelationIdInterceptor")
public class CorrelationIdInterceptor implements HandlerInterceptor {
  private static final String CORRELATION_ID_ATTR = "correlationId";

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    String correlationId = (String) request.getAttribute(CORRELATION_ID_ATTR);
    try {
      MDC.put(CORRELATION_ID_ATTR, correlationId);
    } catch (IllegalArgumentException e) {
      throw new RuntimeExceptionAuditable(e).wrapAuditableException("", ExceptionSeverity.MEDIUM);
    }

    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    try {
      MDC.remove(CORRELATION_ID_ATTR);
    } catch (IllegalArgumentException e) {
      throw new RuntimeExceptionAuditable(e).wrapAuditableException("", ExceptionSeverity.MEDIUM);
    }
  }
}
