package com.bookelse.dao;

import com.bookelse.dao.executor.InsertQueryExecutor;
import com.bookelse.model.product.Book;
import com.bookelse.model.product.Product;
import com.bookelse.util.datetime.DateTimeUtility;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Repository("BookProductDAO")
@PropertySource("classpath:sql/product-query.properties")
public class BookProductDAO extends ProductDAO implements ProductDAOIN<Book> {

  @Value("${product-add-product-book}")
  private String addProductBookQuery;

  @Override
  public Book addProduct(Book book) throws SQLException {
    Product product = addInProductTable(book);
    InsertQueryExecutor<?> insertQueryExecutor =
        new InsertQueryExecutor<>(addProductBookQuery, jdbcTemplate);
    insertQueryExecutor
        .addArgument(book.getBookId().getId())
        .addArgument(book.getBookName())
        .addArgument(book.getBookAuthor())
        .addArgument(book.getPublisher())
        .addArgument(DateTimeUtility.zonedDateTimeToTimestamp(book.getPublishedOn()))
        .addArgument(product.getProductId().getId())
        .addArgument(DateTimeUtility.zonedDateTimeToTimestamp(book.getCreatedOn()))
        .addArgument(DateTimeUtility.zonedDateTimeToTimestamp(book.getUpdatedOn()));
    insertQueryExecutor.execute();

    boolean isBookAdded = insertQueryExecutor.getNoOfRowsAffected() > 0;
    return isBookAdded ? book : null;
  }
}
