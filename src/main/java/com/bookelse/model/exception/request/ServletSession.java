package com.bookelse.model.exception.request;

import java.util.Map;
import java.util.StringJoiner;

public class ServletSession {
  private final String sessionId;
  private final Long creationTime;
  private final Long lastAccessTime;
  private final Integer maxInactiveInterval;
  private final Map<String, String> attributes;

  public ServletSession(
      Map<String, String> attributes,
      String sessionId,
      Long creationTime,
      Long lastAccessTime,
      Integer maxInactiveInterval) {
    this.attributes = attributes;
    this.sessionId = sessionId;
    this.creationTime = creationTime;
    this.lastAccessTime = lastAccessTime;
    this.maxInactiveInterval = maxInactiveInterval;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ServletSession.class.getSimpleName() + "[", "]")
        .add("attributes=" + attributes)
        .add("sessionId='" + sessionId + "'")
        .add("creationTime=" + creationTime)
        .add("lastAccessTime=" + lastAccessTime)
        .add("maxInactiveInterval=" + maxInactiveInterval)
        .toString();
  }
}
