/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.challenge.ecommerce.repository;

import com.challenge.ecommerce.model.Inventory;
import com.challenge.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author leand
 */
public interface IInventoryRepository extends JpaRepository<Inventory, Long>{
     Inventory findByProduct(Product product);
}
