package com.bookelse.service;

import com.bookelse.dao.ProductDAO;
import com.bookelse.model.id.ProductId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ProductService")
public class ProductService {

  private final ProductDAO productDAO;

  public ProductService(@Qualifier("ProductDAO") ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  protected Boolean isProductExist(ProductId productId) {
    return productDAO.isProductExist(productId);
  }
}
