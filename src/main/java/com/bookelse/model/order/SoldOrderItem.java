package com.bookelse.model.order;

import com.bookelse.model.id.Id;
import com.bookelse.model.inventory.Inventory;
import com.bookelse.model.product.Product;
import com.bookelse.model.user.Customer;
import com.bookelse.model.values.BigDecimalValue;
import java.time.ZonedDateTime;

public class SoldOrderItem<P extends Product> extends OrderItems<P> {
  public SoldOrderItem(
      Id orderItemId,
      BigDecimalValue grossAmount,
      BigDecimalValue netAmount,
      BigDecimalValue discount,
      Order order,
      Customer customer,
      Inventory<P> inventory,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn) {
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
  }
}
