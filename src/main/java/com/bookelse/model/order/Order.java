package com.bookelse.model.order;

import com.bookelse.model.id.Id;
import com.bookelse.model.user.Customer;
import com.bookelse.model.values.BigDecimalValue;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Order {
  private final Id orderId;
  private final Customer customer;
  private final BigDecimalValue orderGrossAmount;
  private final BigDecimalValue orderNetAmount;
  private final BigDecimalValue discount;
  private final Boolean isTransactionOnline;
  private final Boolean isAmountPaid;
  private final PaymentMode paymentMode;
  private final ZonedDateTime createdOn;
  private final ZonedDateTime updatedOn;

  public Order(
      Id orderId,
      Customer customer,
      BigDecimalValue orderGrossAmount,
      BigDecimalValue orderNetAmount,
      BigDecimalValue discount,
      Boolean isTransactionOnline,
      Boolean isAmountPaid,
      PaymentMode paymentMode,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn) {
    this.orderId = orderId;
    this.customer = customer;
    this.orderGrossAmount = orderGrossAmount;
    this.orderNetAmount = orderNetAmount;
    this.discount = discount;
    this.isTransactionOnline = isTransactionOnline;
    this.isAmountPaid = isAmountPaid;
    this.paymentMode = paymentMode;
    this.createdOn = createdOn;
    this.updatedOn = updatedOn;
  }

  public Id getOrderId() {
    return orderId;
  }

  public Customer getCustomer() {
    return customer;
  }

  public BigDecimalValue getOrderGrossAmount() {
    return orderGrossAmount;
  }

  public BigDecimalValue getOrderNetAmount() {
    return orderNetAmount;
  }

  public BigDecimalValue getDiscount() {
    return discount;
  }

  public Boolean getTransactionOnline() {
    return isTransactionOnline;
  }

  public Boolean getAmountPaid() {
    return isAmountPaid;
  }

  public PaymentMode getPaymentMode() {
    return paymentMode;
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
    Order order = (Order) o;
    return Objects.equals(getOrderId(), order.getOrderId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getOrderId());
  }
}
