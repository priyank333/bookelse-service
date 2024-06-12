package com.bookelse.model.exception.request;

import java.util.Map;

public class Cookie {
  private final String cookieName;
  private final String cookieValue;
  private final Integer maxAge;
  private final String path;
  private final String domain;
  private final boolean isSecure;
  private final Map<String, String> attributes;

  public Cookie(
      String cookieName,
      String cookieValue,
      Integer maxAge,
      String path,
      String domain,
      boolean isSecure,
      Map<String, String> attributes) {
    this.cookieName = cookieName;
    this.cookieValue = cookieValue;
    this.maxAge = maxAge;
    this.path = path;
    this.domain = domain;
    this.isSecure = isSecure;
    this.attributes = attributes;
  }
}
