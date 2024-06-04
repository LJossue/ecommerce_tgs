/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.ecommerce.service;

import com.challenge.ecommerce.model.Order;
import com.challenge.ecommerce.model.OrderItem;
import com.challenge.ecommerce.repository.IOrderItemRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leand
 */
@Service
public class OrderItemService implements IOrderItemService {

    @Autowired
    private IOrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem findOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public List<OrderItem> findOrderItemsByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }
}
