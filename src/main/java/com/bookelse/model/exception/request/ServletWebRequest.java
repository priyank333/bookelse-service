package com.bookelse.model.exception.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import org.apache.commons.collections.MapUtils;

public class ServletWebRequest {
  private String requestURI;
  private List<Cookie> cookies;
  private String contextPath;
  private Map<String, String> headers;
  private String authType;
  private String method;
  private Map<String, String[]> parameters;
  private String queryString;
  private String remoteAddress;
  private String remoteHost;
  private String requestURL;
  private String schema;
  private String serverName;
  private Integer serverPort;
  private String servletPath;
  private ServletContext servletContext;
  private String remoteUser;
  private Map<String, String> trailerFields;
  private String pathInfo;
  private String pathTranslated;
  private String requestedSessionId;
  private ServletSession servletSession;

  public ServletWebRequest(
      org.springframework.web.context.request.ServletWebRequest servletWebRequest) {
    loadHTTPServletRequest(servletWebRequest.getRequest());
  }

  private void loadHTTPServletRequest(HttpServletRequest httpServletRequest) {
    if (Objects.isNull(httpServletRequest)) return;
    this.requestURI = httpServletRequest.getRequestURI();
    this.cookies = loadCookies(httpServletRequest.getCookies());
    this.contextPath = httpServletRequest.getContextPath();
    this.headers = loadHeaders(httpServletRequest);
    this.parameters = loadParameters(httpServletRequest);
    this.queryString = httpServletRequest.getQueryString();
    this.remoteAddress = httpServletRequest.getRemoteAddr();
    this.remoteHost = httpServletRequest.getRemoteHost();
    this.requestURL = httpServletRequest.getRequestURL().toString();
    this.servletPath = httpServletRequest.getServletPath();
    this.authType = httpServletRequest.getAuthType();
    this.method = httpServletRequest.getMethod();
    this.schema = httpServletRequest.getScheme();
    this.serverName = httpServletRequest.getServerName();
    this.serverPort = httpServletRequest.getServerPort();
    this.servletContext = loadServletContext(httpServletRequest);
    this.remoteUser = httpServletRequest.getRemoteUser();
    this.trailerFields = httpServletRequest.getTrailerFields();
    this.pathInfo = httpServletRequest.getPathInfo();
    this.pathTranslated = httpServletRequest.getPathTranslated();
    this.requestedSessionId = httpServletRequest.getRequestedSessionId();
    this.servletSession = loadServletSession(httpServletRequest.getSession());
  }

  private List<Cookie> loadCookies(jakarta.servlet.http.Cookie[] cookies) {
    if (Objects.isNull(cookies)) {
      return Collections.emptyList();
    }
    List<Cookie> cookiesList = new ArrayList<>();
    for (jakarta.servlet.http.Cookie cookie : cookies) {
      cookiesList.add(
          new Cookie(
              cookie.getName(),
              cookie.getValue(),
              cookie.getMaxAge(),
              cookie.getPath(),
              cookie.getDomain(),
              cookie.getSecure(),
              cookie.getAttributes()));
    }
    return cookiesList;
  }

  private Map<String, String> loadHeaders(HttpServletRequest httpServletRequest) {
    Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
    if (Objects.isNull(headerNames) || !headerNames.hasMoreElements()) {
      return MapUtils.EMPTY_MAP;
    }
    Map<String, String> headers = new HashMap<>();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      headers.put(headerName, httpServletRequest.getHeader(headerName));
    }
    return headers;
  }

  private Map<String, String[]> loadParameters(HttpServletRequest httpServletRequest) {
    Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
    if (Objects.isNull(parameterNames) || !parameterNames.hasMoreElements()) {
      return MapUtils.EMPTY_MAP;
    }
    Map<String, String[]> parameters = new HashMap<>();
    while (parameterNames.hasMoreElements()) {
      String parameterName = parameterNames.nextElement();
      parameters.put(parameterName, httpServletRequest.getParameterValues(parameterName));
    }
    return parameters;
  }

  private ServletContext loadServletContext(HttpServletRequest httpServletRequest) {
    jakarta.servlet.ServletContext servletContext = httpServletRequest.getServletContext();
    if (Objects.isNull(servletContext)) {
      return null;
    }
    Enumeration<String> attributes = httpServletRequest.getServletContext().getAttributeNames();
    Map<String, String> attributeMap = new HashMap<>();
    while ((!Objects.isNull(attributes)) && attributes.hasMoreElements()) {
      String attributeName = attributes.nextElement();
      Object attributeValue = httpServletRequest.getAttribute(attributeName);
      attributeMap.put(attributeName, String.valueOf(attributeValue));
    }
    Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
    Map<String, String> initParameterMap = new HashMap<>();
    while ((!Objects.isNull(initParameterNames)) && initParameterNames.hasMoreElements()) {
      String initParameterName = initParameterNames.nextElement();
      String initParameterValue = servletContext.getInitParameter(initParameterName);
      initParameterMap.put(initParameterName, initParameterValue);
    }
    return new ServletContext(
        attributeMap,
        servletContext.getContextPath(),
        initParameterMap,
        servletContext.getRequestCharacterEncoding(),
        servletContext.getServerInfo(),
        servletContext.getServletContextName(),
        servletContext.getSessionTimeout(),
        servletContext.getVirtualServerName());
  }

  private ServletSession loadServletSession(HttpSession session) {
    Enumeration<String> attributeNames = session.getAttributeNames();
    Map<String, String> attributeMap = new HashMap<>();
    if ((!Objects.isNull(attributeNames)) && attributeNames.hasMoreElements()) {
      while (attributeNames.hasMoreElements()) {
        String attributeName = attributeNames.nextElement();
        Object attributeValue = session.getAttribute(attributeName);
        attributeMap.put(attributeName, String.valueOf(attributeValue));
      }
    }

    return new ServletSession(
        attributeMap,
        session.getId(),
        session.getCreationTime(),
        session.getLastAccessedTime(),
        session.getMaxInactiveInterval());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ServletWebRequest.class.getSimpleName() + "[", "]")
        .add("authType='" + authType + "'")
        .add("requestURI='" + requestURI + "'")
        .add("cookies=" + cookies)
        .add("contextPath='" + contextPath + "'")
        .add("headers=" + headers)
        .add("method='" + method + "'")
        .add("parameters=" + parameters)
        .add("queryString='" + queryString + "'")
        .add("remoteAddress='" + remoteAddress + "'")
        .add("remoteHost='" + remoteHost + "'")
        .add("requestURL='" + requestURL + "'")
        .add("schema='" + schema + "'")
        .add("serverName='" + serverName + "'")
        .add("serverPort=" + serverPort)
        .add("servletPath='" + servletPath + "'")
        .add("servletContext=" + servletContext)
        .add("remoteUser='" + remoteUser + "'")
        .add("trailerFields=" + trailerFields)
        .add("pathInfo='" + pathInfo + "'")
        .add("pathTranslated='" + pathTranslated + "'")
        .add("requestedSessionId='" + requestedSessionId + "'")
        .add("servletSession=" + servletSession)
        .toString();
  }
}
