package com.bookelse.service;

import com.bookelse.dao.InventoryDAO;
import com.bookelse.model.id.InventoryId;
import com.bookelse.model.id.ProductId;
import com.bookelse.model.id.VendorId;
import com.bookelse.model.inventory.Inventory;
import com.bookelse.model.product.Product;
import com.bookelse.model.values.BigDecimalValue;
import com.bookelse.payload.inventory.UpdateProductInventoryRequestPayload;
import com.bookelse.util.datetime.DateTimeUtility;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("InventoryService")
public class InventoryService<PType extends Product> {
  private final VendorService vendorService;
  private final ProductService productService;
  private final InventoryDAO inventoryDAO;

  protected InventoryService(
      @Qualifier("VendorService") VendorService vendorService,
      @Qualifier("ProductService") ProductService productService,
      @Qualifier("InventoryDAO") InventoryDAO inventoryDAO) {
    this.vendorService = vendorService;
    this.productService = productService;
    this.inventoryDAO = inventoryDAO;
  }

  public int updateInventories(
      UpdateProductInventoryRequestPayload updateProductInventoryRequestPayload) {
    int noOfRowsAffected = 0;
    if (vendorService.isVendorExist(new VendorId(updateProductInventoryRequestPayload.vendorId()))
        && productService.isProductExist(
            new ProductId(updateProductInventoryRequestPayload.productId()))) {
      List<Inventory<?>> inventories = buildInventories(updateProductInventoryRequestPayload);
      noOfRowsAffected = inventoryDAO.addInventoriesInDB(inventories);
    }
    return noOfRowsAffected;
  }

  private List<Inventory<?>> buildInventories(
      UpdateProductInventoryRequestPayload updateProductInventoryRequestPayload) {
    int quantity = updateProductInventoryRequestPayload.quantity();
    List<Inventory<?>> inventories = new ArrayList<>();
    for (int iter = 0; iter < quantity; iter++) {
      if (StringUtils.isNotBlank(updateProductInventoryRequestPayload.purchaseGrossAmount())
          && StringUtils.isNotBlank(updateProductInventoryRequestPayload.purchaseDiscount())) {
        BigDecimalValue purchaseGrossAmount =
            BigDecimalValue.valueOf(updateProductInventoryRequestPayload.purchaseGrossAmount());
        BigDecimalValue purchaseDiscount =
            BigDecimalValue.valueOf(updateProductInventoryRequestPayload.purchaseDiscount());
        BigDecimalValue purchaseNetAmount =
            purchaseGrossAmount.subtractValue(purchaseGrossAmount.percentage(purchaseDiscount));

        Inventory<?> inventory =
            new Inventory<>(
                new InventoryId(),
                new ProductId(updateProductInventoryRequestPayload.productId()),
                new VendorId(updateProductInventoryRequestPayload.vendorId()),
                Boolean.TRUE,
                purchaseGrossAmount,
                purchaseNetAmount,
                purchaseDiscount,
                DateTimeUtility.getCurrentUTCDateTime(),
                DateTimeUtility.getCurrentUTCDateTime());
        inventories.add(inventory);
      }
    }
    return inventories;
  }
}
