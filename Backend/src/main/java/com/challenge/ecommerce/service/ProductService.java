/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.ecommerce.service;

import com.challenge.ecommerce.model.Product;
import com.challenge.ecommerce.repository.IProductRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leand
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository proRepo;

    @Override
    @Transactional
    public void saveProduct(Product product) {
        proRepo.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return proRepo.findAll();
    }

    @Override
    public Product findProduct(Long id) {
        return proRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void editProduct(Long originalId, String newName, String newDescription, Double newPrice, Integer newStock, String newImage) {
        Product pro = this.findProduct(originalId);
        if (pro != null) {
            pro.setName(newName);
            pro.setDescription(newDescription);
            pro.setPrice(newPrice);
            pro.setStock(newStock);
            pro.setImage(newImage);
            this.saveProduct(pro);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        proRepo.deleteById(id);
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return proRepo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> getAvailableProducts() {
        return proRepo.findByStockGreaterThan(0);
    }

    @Override
    @Transactional
    public void updateProductStock(Long id, Integer newStock) {
        Product pro = this.findProduct(id);
        if (pro != null) {
            pro.setStock(newStock);
            this.saveProduct(pro);
        }
    }
}