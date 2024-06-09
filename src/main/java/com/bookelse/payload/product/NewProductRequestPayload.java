package com.bookelse.payload.product;

import com.bookelse.model.product.ProductFeatures;

public class NewProductRequestPayload {
  private String productName;
  private String productMRP;
  private String productDiscount;
  private ProductFeatures productFeatures;
  private String vendorId;
  private String purchaseGrossAmount;
  private String purchaseDiscount;

  public String getProductName() {
    return productName;
  }

  public String getProductMRP() {
    return productMRP;
  }

  public String getProductDiscount() {
    return productDiscount;
  }

  public ProductFeatures getProductFeatures() {
    return productFeatures;
  }

  public String getVendorId() {
    return vendorId;
  }

  public String getPurchaseGrossAmount() {
    return purchaseGrossAmount;
  }

  public String getPurchaseDiscount() {
    return purchaseDiscount;
  }
}
