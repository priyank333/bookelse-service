package com.bookelse.dao;

import com.bookelse.config.ApplicationContextConfiguration;
import com.bookelse.dao.executor.InsertQueryExecutor;
import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.exception.ExceptionSeverity;
import com.bookelse.model.id.ProductId;
import com.bookelse.model.product.Product;
import com.bookelse.util.datetime.DateTimeUtility;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("ProductDAO")
@PropertySource("classpath:sql/product-query.properties")
public class ProductDAO {

  protected final JdbcTemplate jdbcTemplate;

  @Value("${product.is-product-exist}")
  private String isProductExistQuery;

  @Value("${product-add-product}")
  private String addProductQuery;

  public ProductDAO() {
    jdbcTemplate =
        ApplicationContextConfiguration.getApplicationContext().getBean(JdbcTemplate.class);
  }

  public Boolean isProductExist(ProductId productId) {
    try {
      Integer count =
          jdbcTemplate.queryForObject(isProductExistQuery, Integer.class, productId.getId());
      return count != null && count > 0;
    } catch (DataAccessException e) {
      RuntimeExceptionAuditable runtimeExceptionAuditable = new RuntimeExceptionAuditable(e);
      runtimeExceptionAuditable.wrapAuditableException("", ExceptionSeverity.HIGH);
      throw runtimeExceptionAuditable;
    }
  }

  protected Product addInProductTable(Product product) throws SQLException {
    InsertQueryExecutor<?> insertQueryExecutor =
        new InsertQueryExecutor<>(addProductQuery, jdbcTemplate);
    insertQueryExecutor
        .addArgument(product.getProductId().getId())
        .addArgument(product.getProductName())
        .addArgument(product.getProductMRP().round(2).getValue())
        .addArgument(product.getProductDiscount().round(2).getValue())
        .addArgument(product.getProductNetPrice().round(2).getValue())
        .addArgument(product.getProductFeatures().toJSON())
        .addArgument(DateTimeUtility.zonedDateTimeToTimestamp(product.getCreatedOn()))
        .addArgument(DateTimeUtility.zonedDateTimeToTimestamp(product.getUpdatedOn()));
    insertQueryExecutor.execute();
    return insertQueryExecutor.getNoOfRowsAffected() > 0 ? product : null;
  }
}
