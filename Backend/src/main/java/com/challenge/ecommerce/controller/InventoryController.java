/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.ecommerce.controller;

import com.challenge.ecommerce.model.Inventory;
import com.challenge.ecommerce.model.Product;
import com.challenge.ecommerce.service.IInventoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leand
 */
@RestController
@RequestMapping("/inventories")
@CrossOrigin(origins = "http://localhost:3000")

public class InventoryController {
    @Autowired
    private IInventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        inventoryService.saveInventory(inventory);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        Inventory inventory = inventoryService.findInventoryById(id);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Inventory> getInventoryByProduct(@PathVariable Long productId) {
        Product product = new Product();
        product.setId(productId);
        Inventory inventory = inventoryService.findInventoryByProduct(product);
        return ResponseEntity.ok(inventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestParam Integer quantity) {
        inventoryService.updateInventory(id, quantity);
        Inventory inventory = inventoryService.findInventoryById(id);
        return ResponseEntity.ok(inventory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}