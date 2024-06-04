/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.challenge.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.challenge.ecommerce.model.Product;
import java.util.List;
/**
 *
 * @author leand
 */
public interface IProductRepository extends JpaRepository<Product, Long>{
     List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByStockGreaterThan(int stock);
}
