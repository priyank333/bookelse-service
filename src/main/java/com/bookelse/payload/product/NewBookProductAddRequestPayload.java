package com.bookelse.payload.product;

import java.time.ZonedDateTime;

public class NewBookProductAddRequestPayload extends NewProductRequestPayload {
  private String bookName;
  private String bookAuthor;
  private String publisher;
  private ZonedDateTime publishedOn;

  public String getBookName() {
    return bookName;
  }

  public String getBookAuthor() {
    return bookAuthor;
  }

  public String getPublisher() {
    return publisher;
  }

  public ZonedDateTime getPublishedOn() {
    return publishedOn;
  }
}
