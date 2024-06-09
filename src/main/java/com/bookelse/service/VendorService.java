package com.bookelse.service;

import com.bookelse.dao.VendorDAO;
import com.bookelse.model.id.VendorId;
import com.bookelse.model.vendor.Vendor;
import java.sql.SQLException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("VendorService")
public class VendorService {

  VendorDAO vendorDAO;

  public VendorService(@Qualifier("VendorDAO") VendorDAO vendorDAO) {
    this.vendorDAO = vendorDAO;
  }

  public Vendor addNewVendor(Vendor vendor) {
    try {
      return vendorDAO.addVendorInDB(vendor);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Boolean isVendorExist(VendorId vendorId) {
    return vendorDAO.isVendorExist(vendorId);
  }

  public Vendor getVendorDetails(VendorId vendorId) {
    try {
      Optional<Vendor> vendor = vendorDAO.getVendorDetails(vendorId);
      return vendor.orElse(null);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
