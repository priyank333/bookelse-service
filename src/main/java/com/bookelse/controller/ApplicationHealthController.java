package com.bookelse.controller;

import com.bookelse.model.values.DoubleValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationHealthController {

  @GetMapping("health")
  public ResponseEntity<Object> health() {
    DoubleValue doubleValue = DoubleValue.valueOf("10000002");
    DoubleValue percentage = DoubleValue.valueOf("23.34");
    return ResponseEntity.status(HttpStatus.OK).body(doubleValue.percentage(percentage).round(2));
  }
}
