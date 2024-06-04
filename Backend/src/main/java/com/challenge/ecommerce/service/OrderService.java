/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.ecommerce.service;

import com.challenge.ecommerce.model.Order;
import com.challenge.ecommerce.model.UserEntity;
import com.challenge.ecommerce.repository.IOrderRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leand
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    @Transactional
    public void saveOrder(Order order) {
        applyDiscounts(order);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> findOrdersByUser(UserEntity user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public void applyDiscounts(Order order) {
        // Implementar lógica para aplicar descuentos según los casos especiales:
        // a) Descuento del 10% en un rango de tiempo.
        // b) Descuento del 50% para pedidos aleatorios.
        // c) Descuento adicional del 5% para clientes frecuentes.
    }
}
