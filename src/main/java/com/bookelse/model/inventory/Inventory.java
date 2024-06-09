package com.bookelse.model.inventory;

import com.bookelse.model.id.Id;
import com.bookelse.model.id.InventoryId;
import com.bookelse.model.id.ProductId;
import com.bookelse.model.id.VendorId;
import com.bookelse.model.product.Product;
import com.bookelse.model.values.BigDecimalValue;
import com.bookelse.model.vendor.Vendor;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Inventory<P extends Product> {
  private final InventoryId inventoryId;
  private final Boolean isPresentInInventory;
  private final BigDecimalValue purchaseGrossAmount;
  private final BigDecimalValue purchaseNetAmount;
  private final BigDecimalValue discount;
  private final ZonedDateTime createdOn;
  private final ZonedDateTime updatedOn;
  private P product;
  private Vendor vendor;
  private ProductId productId;
  private VendorId vendorId;

  public Inventory(
      InventoryId inventoryId,
      P product,
      Vendor vendor,
      Boolean isPresentInInventory,
      BigDecimalValue purchaseGrossAmount,
      BigDecimalValue purchaseNetAmount,
      BigDecimalValue discount,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn) {
    this.inventoryId = inventoryId;
    this.product = product;
    this.vendor = vendor;
    this.isPresentInInventory = isPresentInInventory;
    this.purchaseGrossAmount = purchaseGrossAmount;
    this.purchaseNetAmount = purchaseNetAmount;
    this.discount = discount;
    this.createdOn = createdOn;
    this.updatedOn = updatedOn;
  }

  public Inventory(
      InventoryId inventoryId,
      ProductId productId,
      VendorId vendorId,
      Boolean isPresentInInventory,
      BigDecimalValue purchaseGrossAmount,
      BigDecimalValue purchaseNetAmount,
      BigDecimalValue discount,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn) {
    this.inventoryId = inventoryId;
    this.productId = productId;
    this.vendorId = vendorId;
    this.isPresentInInventory = isPresentInInventory;
    this.purchaseGrossAmount = purchaseGrossAmount;
    this.purchaseNetAmount = purchaseNetAmount;
    this.discount = discount;
    this.createdOn = createdOn;
    this.updatedOn = updatedOn;
  }

  public Id getInventoryId() {
    return inventoryId;
  }

  public P getProduct() {
    return product;
  }

  public Vendor getVendor() {
    return vendor;
  }

  public ProductId getProductId() {
    return productId;
  }

  public VendorId getVendorId() {
    return vendorId;
  }

  public Boolean getPresentInInventory() {
    return isPresentInInventory;
  }

  public BigDecimalValue getPurchaseGrossAmount() {
    return purchaseGrossAmount;
  }

  public BigDecimalValue getPurchaseNetAmount() {
    return purchaseNetAmount;
  }

  public BigDecimalValue getDiscount() {
    return discount;
  }

  public ZonedDateTime getCreatedOn() {
    return createdOn;
  }

  public ZonedDateTime getUpdatedOn() {
    return updatedOn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Inventory<?> inventory = (Inventory<?>) o;
    return Objects.equals(getInventoryId(), inventory.getInventoryId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getInventoryId());
  }
}
