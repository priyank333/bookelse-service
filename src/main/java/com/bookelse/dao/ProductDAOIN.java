package com.bookelse.dao;

import com.bookelse.model.product.Product;

public interface ProductDAOIN<PType extends Product> {
  PType addProduct(PType pType) ;
}
