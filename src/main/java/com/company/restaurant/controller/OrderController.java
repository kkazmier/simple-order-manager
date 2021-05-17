package com.company.restaurant.controller;

import com.company.restaurant.domain.Order;
import com.company.restaurant.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/")
@AllArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping("all")
    List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody Order order){
        orderService.saveOrder(order);
    }
}
