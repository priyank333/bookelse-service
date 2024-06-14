package com.bookelse.controller.inventory;

import com.bookelse.payload.inventory.UpdateProductInventoryRequestPayload;
import com.bookelse.service.BookInventoryService;
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

  private final BookInventoryService bookBookInventoryService;

  public InventoryController(
      @Qualifier("BookInventoryService") BookInventoryService bookBookInventoryService) {
    this.bookBookInventoryService = bookBookInventoryService;
  }

  @PostMapping("v1/update-book-inventory")
  public ResponseEntity<Integer> updateInventories(
      @RequestBody UpdateProductInventoryRequestPayload updateProductInventoryRequestPayload) {
    int rows = bookBookInventoryService.updateInventories(updateProductInventoryRequestPayload);
    return ResponseEntity.status(HttpStatus.CREATED).body(rows);
  }
}
