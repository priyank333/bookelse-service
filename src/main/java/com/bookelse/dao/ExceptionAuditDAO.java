package com.bookelse.dao;

import com.bookelse.config.ApplicationContextConfiguration;
import com.bookelse.dao.executor.InsertQueryExecutor;
import com.bookelse.model.exception.AuditableExceptionWrapper;
import com.bookelse.util.datetime.DateTimeUtility;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("ExceptionAuditDAO")
@PropertySource("classpath:sql/exception-audit-query.properties")
public class ExceptionAuditDAO {
  protected final JdbcTemplate jdbcTemplate;

  @Value("${exception-audit-insert}")
  private String addExceptionAuditQuery;

  public ExceptionAuditDAO() {
    jdbcTemplate =
        ApplicationContextConfiguration.getApplicationContext().getBean(JdbcTemplate.class);
  }

  public <T extends Throwable> AuditableExceptionWrapper<T> addExceptionAudit(
      AuditableExceptionWrapper<T> auditableExceptionWrapper) throws SQLException {
    InsertQueryExecutor<?> insertQueryExecutor =
        new InsertQueryExecutor<>(addExceptionAuditQuery, jdbcTemplate);
    insertQueryExecutor
        .addArgument(auditableExceptionWrapper.getExceptionId().getId())
        .addArgument(auditableExceptionWrapper.getUserId())
        .addArgument(
            DateTimeUtility.zonedDateTimeToTimestamp(DateTimeUtility.getCurrentUTCDateTime()))
        .addArgument(auditableExceptionWrapper.getExceptionType())
        .addArgument(auditableExceptionWrapper.getStackTrace())
        .addArgument(auditableExceptionWrapper.getDetailedMessage())
        .addArgument(auditableExceptionWrapper.getEnvironment())
        .addArgument(auditableExceptionWrapper.getSeverity().getDescription())
        .addArgument(auditableExceptionWrapper.getCorrelationId())
        .addArgument(auditableExceptionWrapper.getAdditionalDataAsJsonString())
        .addArgument(auditableExceptionWrapper.getWebRequestAsJsonString());
    insertQueryExecutor.execute();
    return insertQueryExecutor.getNoOfRowsAffected() > 0 ? auditableExceptionWrapper : null;
  }
}
