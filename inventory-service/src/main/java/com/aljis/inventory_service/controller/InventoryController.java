package com.aljis.inventory_service.controller;

import com.aljis.inventory_service.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory")
    public ResponseEntity<Boolean> isInventoryInStock(@RequestParam String skuCode, @RequestParam Integer quantity ) {
      log.info("Checking inventory is in stock by Skucode {} and Quantity {}", skuCode, quantity);

        boolean inStock = inventoryService.isInStock(skuCode, quantity);
        return ResponseEntity.ok(inStock);
    }
}
