package com.bookelse.controller.inventory;

import com.bookelse.payload.inventory.UpdateProductInventoryRequestPayload;
import com.bookelse.service.InventoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inventory")
public class InventoryController {

  private final InventoryService<?> inventoryService;

  public InventoryController(@Qualifier("InventoryService") InventoryService<?> inventoryService) {
    this.inventoryService = inventoryService;
  }

  @PostMapping("update-book-inventory")
  public ResponseEntity<Integer> updateInventories(
      @RequestBody UpdateProductInventoryRequestPayload updateProductInventoryRequestPayload) {
    int rows = inventoryService.updateInventories(updateProductInventoryRequestPayload);
    return ResponseEntity.status(HttpStatus.CREATED).body(rows);
  }
}
