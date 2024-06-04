/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.challenge.ecommerce.service;

import com.challenge.ecommerce.model.Order;
import com.challenge.ecommerce.model.OrderItem;
import java.util.List;

/**
 *
 * @author leand
 */
public interface IOrderItemService {
    void saveOrderItem(OrderItem orderItem);

    List<OrderItem> getAllOrderItems();

    OrderItem findOrderItemById(Long id);

    void deleteOrderItem(Long id);

    List<OrderItem> findOrderItemsByOrder(Order order);
}
