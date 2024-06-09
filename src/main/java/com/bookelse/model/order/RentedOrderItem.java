package com.bookelse.model.order;

import com.bookelse.model.id.Id;
import com.bookelse.model.inventory.Inventory;
import com.bookelse.model.product.Product;
import com.bookelse.model.user.Customer;
import com.bookelse.model.values.BigDecimalValue;
import java.time.ZonedDateTime;

public class RentedOrderItem<P extends Product> extends OrderItems<P> {

  private final BigDecimalValue receivedDeposit;
  private final BigDecimalValue receivedDepreciation;
  private final ZonedDateTime rentedFrom;
  private final ZonedDateTime rentedTo;
  private final ZonedDateTime lastDateToReturn;

  public RentedOrderItem(
      Id orderItemId,
      BigDecimalValue grossAmount,
      BigDecimalValue netAmount,
      BigDecimalValue discount,
      Order order,
      Customer customer,
      Inventory<P> inventory,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn,
      BigDecimalValue receivedDeposit,
      BigDecimalValue receivedDepreciation,
      ZonedDateTime rentedFrom,
      ZonedDateTime rentedTo,
      ZonedDateTime lastDateToReturn) {
    super(
        orderItemId,
        grossAmount,
        netAmount,
        discount,
        order,
        customer,
        inventory,
        createdOn,
        updatedOn);
    this.receivedDeposit = receivedDeposit;
    this.receivedDepreciation = receivedDepreciation;
    this.rentedFrom = rentedFrom;
    this.rentedTo = rentedTo;
    this.lastDateToReturn = lastDateToReturn;
  }

  public BigDecimalValue getReceivedDeposit() {
    return receivedDeposit;
  }

  public BigDecimalValue getReceivedDepreciation() {
    return receivedDepreciation;
  }

  public ZonedDateTime getRentedFrom() {
    return rentedFrom;
  }

  public ZonedDateTime getRentedTo() {
    return rentedTo;
  }

  public ZonedDateTime getLastDateToReturn() {
    return lastDateToReturn;
  }
}
