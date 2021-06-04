package com.company.restaurant.controller;

import com.company.restaurant.domain.Order;
import com.company.restaurant.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("order")
public class OrderController {
    private final OrderServiceImpl orderService;

    @ModelAttribute("allOrders")
    public List<Order> getOrdersList(){
        return orderService.getAllOrders();
    }



    @GetMapping("/all")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/orders")
    public String orders(Model model){
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @ModelAttribute(value = "order")
    public Order getNewOrder()
    {
        return new Order();
    }


    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody Order order){
        orderService.saveOrder(order);
    }

    @GetMapping(value = "orderForm")
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping(value = "orderForm")
    public String addNewOrder(@ModelAttribute("order") Order order, Model model){
        model.addAttribute("order", new Order());
        orderService.saveOrder(order);
        System.out.println("New order added.");
        return "redirect:orders";
    }

//    @RequestMapping(value = "removeOrder", params = "removeOrder")
//    public String removeOrder(final Order order, final BindingResult bindingResult, final HttpServletRequest req){
//        final Long orderId = Long.valueOf(req.getParameter("removeOrder"));
//        orderService.deleteOrderById(orderId);
//        System.out.println("Try remove order having id: " + orderId);
//        return "redirect:orders";
//    }

    @RequestMapping(value = "removeOrder/{id}", method = RequestMethod.GET)
    public String removeOrder(@PathVariable("id") long id){
        System.out.println("Try remove order having id: " + id);
        orderService.deleteOrderById(id);
        return "redirect:http://localhost:8080/order/orders";
    }

    @GetMapping(value = "editOrder/{id}")
    public String editOrder(@PathVariable("id") long id){
        return "redirect:http://localhost:8080/order/orders";
    }
}
