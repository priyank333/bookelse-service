package com.bookelse.controller.product;

import com.bookelse.model.product.Book;
import com.bookelse.payload.product.NewBookProductAddRequestPayload;
import com.bookelse.service.BookProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("product")
public class ProductController {

  private final BookProductService bookProductService;

  public ProductController(@Qualifier("BookProductService") BookProductService bookProductService) {
    this.bookProductService = bookProductService;
  }

  @PostMapping("v1/add-book")
  public ResponseEntity<Book> addNewProduct(
      @RequestBody NewBookProductAddRequestPayload newBookProductAddRequestPayload) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(bookProductService.addProduct(newBookProductAddRequestPayload));
  }
}
