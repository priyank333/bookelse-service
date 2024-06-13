package com.bookelse.service;

import com.bookelse.dao.VendorDAO;
import com.bookelse.model.id.VendorId;
import com.bookelse.model.vendor.Vendor;
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
    return vendorDAO.addVendorInDB(vendor);
  }

  public Boolean isVendorExist(VendorId vendorId) {
    return vendorDAO.isVendorExist(vendorId);
  }

  public Vendor getVendorDetails(VendorId vendorId) {
    Optional<Vendor> vendor = vendorDAO.getVendorDetails(vendorId);
    return vendor.orElse(null);
  }
}
