package com.bookelse.dao;

import com.bookelse.config.ApplicationContextConfiguration;
import com.bookelse.dao.executor.InsertQueryExecutor;
import com.bookelse.dao.executor.SelectQueryExecutor;
import com.bookelse.dao.mapper.VendorRowMapper;
import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.exception.ExceptionSeverity;
import com.bookelse.model.id.VendorId;
import com.bookelse.model.vendor.Vendor;
import com.bookelse.util.datetime.DateTimeUtility;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("VendorDAO")
@PropertySource("classpath:sql/vendor-query.properties")
public class VendorDAO {
  private final JdbcTemplate jdbcTemplate;

  @Value("${vendor.add-vendor}")
  private String insertNewVendorQuery;

  @Value("${vendor.is-vendor-exist-by-vendor-id}")
  private String isVendorExistByVendorIdQuery;

  @Value("${vendor.get-vendor-by-id}")
  private String getVendorByIdQuery;

  public VendorDAO() {
    jdbcTemplate =
        ApplicationContextConfiguration.getApplicationContext().getBean(JdbcTemplate.class);
  }

  public Vendor addVendorInDB(Vendor vendor)  {
    InsertQueryExecutor<?> insertQueryExecutor =
        new InsertQueryExecutor<>(insertNewVendorQuery, jdbcTemplate);
    insertQueryExecutor
        .addArgument(vendor.getVendorId().getId())
        .addArgument(vendor.getName().getFirstName())
        .addArgument(vendor.getName().getLastName())
        .addArgument(vendor.getContact().getCountryCode())
        .addArgument(vendor.getContact().getContact())
        .addArgument(vendor.getEmailId().getEmailId())
        .addArgument(vendor.getActive())
        .addArgument(DateTimeUtility.zonedDateTimeToTimestamp(vendor.getCreatedOn()))
        .addArgument(DateTimeUtility.zonedDateTimeToTimestamp(vendor.getUpdatedOn()));
    insertQueryExecutor.execute();
    return insertQueryExecutor.getNoOfRowsAffected() > 0 ? vendor : null;
  }

  public Boolean isVendorExist(VendorId vendorId) {
    try {
      Integer count =
          jdbcTemplate.queryForObject(
              isVendorExistByVendorIdQuery, Integer.class, vendorId.getId());
      return count != null && count > 0;
    } catch (DataAccessException e) {
      RuntimeExceptionAuditable runtimeExceptionAuditable = new RuntimeExceptionAuditable(e);
      runtimeExceptionAuditable.wrapAuditableException("", ExceptionSeverity.HIGH);
      throw runtimeExceptionAuditable;
    }
  }

  public Optional<Vendor> getVendorDetails(VendorId vendorId)  {
    SelectQueryExecutor<VendorRowMapper, Vendor> selectQueryExecutor =
        new SelectQueryExecutor<>(getVendorByIdQuery, jdbcTemplate, new VendorRowMapper());
    selectQueryExecutor.addArgument(vendorId.getId());
    selectQueryExecutor.execute();
    return selectQueryExecutor.getFirstResult();
  }
}
