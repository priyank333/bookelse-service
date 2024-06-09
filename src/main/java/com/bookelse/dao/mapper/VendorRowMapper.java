package com.bookelse.dao.mapper;

import com.bookelse.model.common.Contact;
import com.bookelse.model.common.EmailId;
import com.bookelse.model.common.PersonName;
import com.bookelse.model.id.VendorId;
import com.bookelse.model.vendor.Vendor;
import com.bookelse.util.datetime.DateTimeUtility;
import com.bookelse.util.datetime.ZoneId;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class VendorRowMapper extends CustomRowMapper<Vendor> {
  @Override
  public Vendor mapRow(ResultSet rs, int rowNum) throws SQLException {
    Set<String> columns = loadColumns(rs);
    if (!columns.contains("vendor_id")) {
      return null;
    }
    return new Vendor(
        new VendorId(rs.getString("vendor_id")),
        new PersonName(rs.getString("first_name"), rs.getString("last_name")),
        new Contact(rs.getString("ctr_code"), rs.getString("vendor_contact")),
        new EmailId(rs.getString("email_id")),
        rs.getBoolean("is_active"),
        DateTimeUtility.timestampToZonedDateTime(rs.getTimestamp("created_on"), ZoneId.UTC),
        DateTimeUtility.timestampToZonedDateTime(rs.getTimestamp("lst_update_on"), ZoneId.UTC));
  }
}