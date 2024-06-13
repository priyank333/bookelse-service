package com.bookelse.http.filter;

import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.exception.ExceptionSeverity;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component("CorrelationIdFilter")
public class CorrelationIdFilter implements Filter {
  private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
  private static final String CORRELATION_ID_ATTRIBUTE = "correlationId";

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    processRequest(httpServletRequest);
    try {
      filterChain.doFilter(servletRequest, servletResponse);
    } catch (IOException | ServletException e) {
      throw new RuntimeExceptionAuditable(e).wrapAuditableException("", ExceptionSeverity.HIGH);
    }
  }

  private void processRequest(HttpServletRequest request) {
    String correlationId = request.getHeader(CORRELATION_ID_HEADER);
    if (correlationId == null) {
      correlationId = UUID.randomUUID().toString();
    }
    request.setAttribute(CORRELATION_ID_ATTRIBUTE, correlationId);
  }
}
