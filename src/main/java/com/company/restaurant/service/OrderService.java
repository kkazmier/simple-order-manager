package com.company.restaurant.service;

import com.company.restaurant.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order saveOrder(Order order);
    List<Order> getAllOrders();
    Optional<Order> findOrderById(Long id) throws Exception;
    void deleteOrderById(Long id);
}
