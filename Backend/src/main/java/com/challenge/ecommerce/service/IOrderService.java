/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.challenge.ecommerce.service;

import com.challenge.ecommerce.model.Order;
import com.challenge.ecommerce.model.UserEntity;
import java.util.List;

/**
 *
 * @author leand
 */
public interface IOrderService {
     void saveOrder(Order order);

    List<Order> getAllOrders();

    Order findOrderById(Long id);

    void deleteOrder(Long id);

    List<Order> findOrdersByUser(UserEntity user);

    void applyDiscounts(Order order);
}
