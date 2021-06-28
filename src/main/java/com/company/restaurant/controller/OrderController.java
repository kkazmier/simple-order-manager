package com.company.restaurant.controller;

import com.company.restaurant.domain.Address;
import com.company.restaurant.domain.Order;
import com.company.restaurant.service.AddressServiceImpl;
import com.company.restaurant.service.DriverServiceImpl;
import com.company.restaurant.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("order")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderServiceImpl orderService;
    private final AddressServiceImpl addressService;
    private final DriverServiceImpl driverService;

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
        Order order = new Order();
        Address address = new Address();
        address.setStreet("...");
//        address.setOrder(order);
//        order.setAddress(address);
        model.addAttribute("order", order);
        model.addAttribute("address", address);
        return "orderForm";
    }

    @PostMapping(value = "orderForm")
    public String addNewOrder(@ModelAttribute("order") Order order, @ModelAttribute("address") Address address, Model model){
        LocalTime time = LocalTime.now();
        
        logger.info(time.toString());
        order.setCreateTime(time);
        order.setTimeToDelivery(time.plusMinutes(90));
        order.setCloseTime(time.plusMinutes(90));
        order.setSecondsToDelivery(time.plusMinutes(90).toSecondOfDay());
        addressService.save(address);
        orderService.saveOrder(order);
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
        orderService.deleteOrderById(id);
        logger.info("Try remove order having id: " + id);
        return "redirect:http://localhost:8080/order/orders";
    }

    @GetMapping(value = "editOrder/{id}")
    public String editOrder(@PathVariable("id") long id){
        return "redirect:http://localhost:8080/order/orders";
    }

    @GetMapping("/addOrder/{orderId}/toDriver")
    public String addOrderToDriver(@PathVariable("orderId") long orderId, Model model){
        model.addAttribute("drivers", driverService.getAllDrivers());
        model.addAttribute("selectedOrder", orderId);
        logger.info("Add order " + orderId + " to driver." );
        return "addOrderToDriver";
    }
}
