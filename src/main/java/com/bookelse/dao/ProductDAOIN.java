package com.bookelse.dao;

import com.bookelse.model.product.Product;
import java.sql.SQLException;

public interface ProductDAOIN<PType extends Product> {
  PType addProduct(PType pType) throws SQLException;
}
