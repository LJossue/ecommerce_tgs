/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.ecommerce.controller;

import com.challenge.ecommerce.model.Order;
import com.challenge.ecommerce.model.OrderItem;
import com.challenge.ecommerce.service.IOrderItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leand
 */
@RestController
@RequestMapping("/order-items")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderItemController {
  @Autowired
    private IOrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        orderItemService.saveOrderItem(orderItem);
        return ResponseEntity.ok(orderItem);
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        OrderItem orderItem = orderItemService.findOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> findOrderItemsByOrder(@PathVariable Long orderId) {
        Order order = new Order();
        order.setId(orderId);
        List<OrderItem> orderItems = orderItemService.findOrderItemsByOrder(order);
        return ResponseEntity.ok(orderItems);
    }
}
