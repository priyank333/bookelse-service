package com.bookelse.model.product;

import com.bookelse.model.id.BookId;
import java.time.ZonedDateTime;

public class Book extends Product {
  protected final BookId bookId;
  protected final String bookName;
  protected final String bookAuthor;
  protected final String publisher;
  protected final ZonedDateTime publishedOn;

  public Book(Builder<?> builder) {
    super(builder);
    this.bookId = builder.bookId;
    this.bookName = builder.bookName;
    this.bookAuthor = builder.bookAuthor;
    this.publisher = builder.publisher;
    this.publishedOn = builder.publishedOn;
  }

  public static Builder<?> builder() {
    return new BookBuilder();
  }

  public BookId getBookId() {
    return bookId;
  }

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

  public abstract static class Builder<B extends Builder<B>> extends Product.Builder<B> {
    private BookId bookId;
    private String bookName;
    private String bookAuthor;
    private String publisher;
    private ZonedDateTime publishedOn;

    public B addBookId(BookId bookId) {
      this.bookId = bookId;
      return self();
    }

    public B addBookName(String bookName) {
      this.bookName = bookName;
      return self();
    }

    public B addBookAuthor(String bookAuthor) {
      this.bookAuthor = bookAuthor;
      return self();
    }

    public B addPublisher(String publisher) {
      this.publisher = publisher;
      return self();
    }

    public B addPublishedOn(ZonedDateTime publishedOn) {
      this.publishedOn = publishedOn;
      return self();
    }

    public Book build() {
      return new Book(this);
    }
  }

  private static class BookBuilder extends Builder<BookBuilder> {
    @Override
    protected BookBuilder self() {
      return this;
    }
  }
}
