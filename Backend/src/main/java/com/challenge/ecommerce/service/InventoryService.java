/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.ecommerce.service;

import com.challenge.ecommerce.model.Inventory;
import com.challenge.ecommerce.model.Product;
import com.challenge.ecommerce.repository.IInventoryRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leand
 */
@Service
public class InventoryService implements IInventoryService {

    @Autowired
    private IInventoryRepository inventoryRepository;

    @Override
    @Transactional
    public void saveInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory findInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    @Override
    public Inventory findInventoryByProduct(Product product) {
        return inventoryRepository.findByProduct(product);
    }

    @Override
    @Transactional
    public void updateInventory(Long id, Integer quantity) {
        Inventory inventory = findInventoryById(id);
        if (inventory != null) {
            inventory.setQuantity(quantity);
            saveInventory(inventory);
        }
    }

    @Override
    @Transactional
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
