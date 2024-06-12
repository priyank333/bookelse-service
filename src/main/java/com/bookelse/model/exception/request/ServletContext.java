package com.bookelse.model.exception.request;

import java.util.Map;

public class ServletContext {
  private final String contextPath;
  private final Map<String, String> initParameters;
  private final Map<String, String> attributes;
  private final String requestCharacterEncoding;
  private final String serverInfo;
  private final String servletContextName;
  private final String virtualServerName;
  private final Integer sessionTimeout;

  public ServletContext(
      Map<String, String> attributes,
      String contextPath,
      Map<String, String> initParameters,
      String requestCharacterEncoding,
      String serverInfo,
      String servletContextName,
      Integer sessionTimeout,
      String virtualServerName) {
    this.attributes = attributes;
    this.contextPath = contextPath;
    this.initParameters = initParameters;
    this.requestCharacterEncoding = requestCharacterEncoding;
    this.serverInfo = serverInfo;
    this.servletContextName = servletContextName;
    this.sessionTimeout = sessionTimeout;
    this.virtualServerName = virtualServerName;
  }
}
