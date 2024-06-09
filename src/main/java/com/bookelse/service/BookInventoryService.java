package com.bookelse.service;

import com.bookelse.dao.BookProductDAO;
import com.bookelse.model.product.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("BookInventoryService")
public class BookInventoryService {
  protected BookInventoryService(
      @Qualifier("VendorService") VendorService vendorService,
      @Qualifier("BookProductService") ProductService productService) {}
}
