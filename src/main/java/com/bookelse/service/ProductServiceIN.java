package com.bookelse.service;

import com.bookelse.model.product.Product;
import com.bookelse.payload.product.NewProductRequestPayload;

public interface ProductServiceIN<T extends NewProductRequestPayload, PType extends Product> {

  PType addProduct(T productRequestPayload);
}
