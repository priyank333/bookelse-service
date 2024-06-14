package com.bookelse.service;

import com.bookelse.dao.InventoryDAO;
import com.bookelse.model.product.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("BookInventoryService")
public class BookInventoryService extends InventoryService<Book> {
  protected BookInventoryService(
      @Qualifier("VendorService") VendorService vendorService,
      @Qualifier("BookProductService") ProductService productService,
      @Qualifier("InventoryDAO") InventoryDAO inventoryDAO) {
    super(vendorService, productService, inventoryDAO);
  }
}
