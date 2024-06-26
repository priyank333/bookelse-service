package com.bookelse.dao;

import com.bookelse.config.ApplicationContextConfiguration;
import com.bookelse.dao.executor.BatchInsertQueryExecutor;
import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.exception.ExceptionSeverity;
import com.bookelse.model.inventory.Inventory;
import com.bookelse.util.datetime.DateTimeUtility;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("InventoryDAO")
@PropertySource("classpath:sql/inventory-query.properties")
public class InventoryDAO {

  protected final JdbcTemplate jdbcTemplate;

  @Value("${inventory.add-inventories}")
  private String addInventoriesQuery;

  public InventoryDAO() {
    jdbcTemplate =
        ApplicationContextConfiguration.getApplicationContext().getBean(JdbcTemplate.class);
  }

  public int addInventoriesInDB(List<Inventory<?>> inventories) {
    BatchInsertQueryExecutor batchInsertQueryExecutor =
        new BatchInsertQueryExecutor(
            addInventoriesQuery,
            new BatchPreparedStatementSetter() {
              @Override
              public void setValues(PreparedStatement ps, int i) {
                Inventory<?> inventory = inventories.get(i);
                try {
                  ps.setString(1, inventory.getInventoryId().getId());
                  ps.setString(2, inventory.getProductId().getId());
                  ps.setString(3, inventory.getVendorId().getId());
                  ps.setBoolean(4, Boolean.FALSE);
                  ps.setBoolean(5, Boolean.TRUE);
                  ps.setObject(6, inventory.getPurchaseGrossAmount().round(2).getValue());
                  ps.setObject(7, inventory.getPurchaseNetAmount().round(2).getValue());
                  ps.setObject(8, inventory.getDiscount().round(2).getValue());
                  ps.setTimestamp(
                      9, DateTimeUtility.zonedDateTimeToTimestamp(inventory.getCreatedOn()));
                  ps.setTimestamp(
                      10, DateTimeUtility.zonedDateTimeToTimestamp(inventory.getUpdatedOn()));
                } catch (SQLException e) {
                  throw new RuntimeExceptionAuditable(e)
                      .wrapAuditableException("", ExceptionSeverity.HIGH);
                }
              }

              @Override
              public int getBatchSize() {
                return inventories.size();
              }
            },
            jdbcTemplate);
    batchInsertQueryExecutor.execute();
    return batchInsertQueryExecutor.getNoOfRowsAffected();
  }
}
