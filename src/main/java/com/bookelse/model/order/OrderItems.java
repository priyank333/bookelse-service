package com.bookelse.model.order;

import com.bookelse.model.id.Id;
import com.bookelse.model.inventory.Inventory;
import com.bookelse.model.product.Product;
import com.bookelse.model.user.Customer;
import com.bookelse.model.values.BigDecimalValue;
import java.time.ZonedDateTime;
import java.util.Objects;

public abstract class OrderItems<P extends Product> {
  private final Id orderItemId;
  private final BigDecimalValue grossAmount;
  private final BigDecimalValue netAmount;
  private final BigDecimalValue discount;
  private final Order order;
  private final Customer customer;
  private final Inventory<P> inventory;
  private final ZonedDateTime createdOn;
  private final ZonedDateTime updatedOn;

  public OrderItems(
      Id orderItemId,
      BigDecimalValue grossAmount,
      BigDecimalValue netAmount,
      BigDecimalValue discount,
      Order order,
      Customer customer,
      Inventory<P> inventory,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn) {
    this.orderItemId = orderItemId;
    this.grossAmount = grossAmount;
    this.netAmount = netAmount;
    this.discount = discount;
    this.order = order;
    this.customer = customer;
    this.inventory = inventory;
    this.createdOn = createdOn;
    this.updatedOn = updatedOn;
  }

  public Id getOrderItemId() {
    return orderItemId;
  }

  public BigDecimalValue getGrossAmount() {
    return grossAmount;
  }

  public BigDecimalValue getNetAmount() {
    return netAmount;
  }

  public BigDecimalValue getDiscount() {
    return discount;
  }

  public Order getOrder() {
    return order;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Inventory<P> getInventory() {
    return inventory;
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
    OrderItems<?> that = (OrderItems<?>) o;
    return Objects.equals(getOrderItemId(), that.getOrderItemId())
        && Objects.equals(getOrder(), that.getOrder())
        && Objects.equals(getCustomer(), that.getCustomer())
        && Objects.equals(getInventory(), that.getInventory());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getOrderItemId(), getOrder(), getCustomer(), getInventory());
  }
}
