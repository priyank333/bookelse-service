package com.bookelse.dao;

import com.bookelse.config.transaction.holder.TransactionDefinitionHolder;
import com.bookelse.config.transaction.holder.TransactionManagerHolder;
import com.bookelse.dao.executor.InsertQueryExecutor;
import com.bookelse.model.product.Book;
import com.bookelse.model.product.Product;
import com.bookelse.util.datetime.DateTimeUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Repository("BookProductDAO")
@PropertySource("classpath:sql/product-query.properties")
public class BookProductDAO extends ProductDAO implements ProductDAOIN<Book> {

  @Value("${product-add-product-book}")
  private String addProductBookQuery;

  @Override
  public Book addProduct(Book book) {
    JdbcTransactionManager transactionManager =
        TransactionManagerHolder.getJdbcTransactionManager();
    DefaultTransactionDefinition transactionDefinition =
        TransactionDefinitionHolder.getDefaultTransactionDefinition();
    transactionDefinition.setName("Add Product Book");
    TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
    InsertQueryExecutor<?> insertQueryExecutor =
        new InsertQueryExecutor<>(addProductBookQuery, jdbcTemplate);
    try {
      Product product = addInProductTable(book);
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
      transactionManager.commit(transactionStatus);
    } catch (DataAccessException e) {
      transactionManager.rollback(transactionStatus);
      throw e;
    }
    boolean isBookAdded = insertQueryExecutor.getNoOfRowsAffected() > 0;
    return isBookAdded ? book : null;
  }
}
