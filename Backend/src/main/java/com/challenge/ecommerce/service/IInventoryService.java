/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.challenge.ecommerce.service;

import com.challenge.ecommerce.model.Inventory;
import com.challenge.ecommerce.model.Product;
import java.util.List;

/**
 *
 * @author leand
 */
public interface IInventoryService {
   void saveInventory(Inventory inventory);

    List<Inventory> getAllInventories();

    Inventory findInventoryById(Long id);

    Inventory findInventoryByProduct(Product product);

    void updateInventory(Long id, Integer quantity);

    void deleteInventory(Long id);
}