package com.bookelse.payload.inventory;

import java.util.Objects;

public record UpdateProductInventoryRequestPayload(
    String vendorId,
    String productId,
    Integer quantity,
    String purchaseGrossAmount,
    String purchaseDiscount) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UpdateProductInventoryRequestPayload that = (UpdateProductInventoryRequestPayload) o;
    return Objects.equals(vendorId, that.vendorId)
        && Objects.equals(productId, that.productId)
        && Objects.equals(quantity, that.quantity)
        && Objects.equals(purchaseDiscount, that.purchaseDiscount)
        && Objects.equals(purchaseGrossAmount, that.purchaseGrossAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vendorId, productId, quantity, purchaseGrossAmount, purchaseDiscount);
  }
}
