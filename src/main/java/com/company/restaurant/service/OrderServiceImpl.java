package com.company.restaurant.service;

import com.company.restaurant.domain.Order;
import com.company.restaurant.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        logger.info("Order saved.");
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> findOrderById(Long id) throws Exception {
        return Optional.of(orderRepository.findById(id).orElseThrow(Exception::new));
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
