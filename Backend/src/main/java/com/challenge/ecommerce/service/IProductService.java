/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.challenge.ecommerce.service;

import com.challenge.ecommerce.model.Product;
import java.util.List;

/**
 *
 * @author leand
 */
public interface IProductService {

    void saveProduct(Product product);

    List<Product> getProducts();

    Product findProduct(Long id);

    void editProduct(Long originalId, String newName, String newDescription, Double newPrice, Integer newStock, String newImage);

    void deleteProduct(Long id);
    
    List<Product> findProductsByName(String name);

    List<Product> getAvailableProducts();

    void updateProductStock(Long id, Integer newStock);
}
