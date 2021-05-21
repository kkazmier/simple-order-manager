package com.company.restaurant.controller;

import com.company.restaurant.domain.Order;
import com.company.restaurant.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@AllArgsConstructor
@Controller
public class OrderController {
    private final OrderServiceImpl orderService;

    @ModelAttribute("ordersList")
    public List<Order> getOrdersList(){
        return orderService.getAllOrders();
    }

    @GetMapping({ "/", "/index" })
    public String main(Model model){
        model.addAttribute("name", "John");
        return "index";
    }

    @GetMapping("all")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("orders")
    public String orders(Model model){
        model.addAttribute("orders", orderService.getAllOrders());
        System.out.println(orderService.getAllOrders().size());
        return "orders.html";
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody Order order){
        orderService.saveOrder(order);
    }
}
