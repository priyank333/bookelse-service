package com.bookelse.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("BookInventoryService")
public class BookInventoryService {
  protected BookInventoryService(
      @Qualifier("VendorService") VendorService vendorService,
      @Qualifier("BookProductService") ProductService productService) {}
}
