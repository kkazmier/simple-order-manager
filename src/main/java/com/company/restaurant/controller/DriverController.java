package com.company.restaurant.controller;

import com.company.restaurant.domain.Driver;
import com.company.restaurant.domain.Order;
import com.company.restaurant.service.DriverServiceImpl;
import com.company.restaurant.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("driver")
public class DriverController {
    private final DriverServiceImpl driverService;
    private final OrderServiceImpl orderService;

    @GetMapping("/allDrivers")
    public String getAllDrivers(Model model){
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "drivers";
    }

    @GetMapping("/driverForm")
    public String driverForm(Model model){
        model.addAttribute("driver", new Driver());
        return "driverForm";
    }

    @PostMapping("/driverForm")
    public String addNewDriver(@ModelAttribute("driver") Driver driver, Model model){
        model.addAttribute("driver", new Driver());
        driverService.saveDriver(driver);
        return "redirect:allDrivers";
    }

    @GetMapping("/removeDriver/{id}")
    public String removeDriver(@PathVariable("id") Long id){
        driverService.deleteDriverById(id);
        return "redirect:/driver/allDrivers";
    }

    @GetMapping("/addOrder/{orderId}/toDriver/{driverId}")
    public String addOrderToDriver(@PathVariable("orderId") Long orderId, @PathVariable("driverId") Long driverId) throws Exception {
        Driver driver = driverService.findDriverById(driverId).orElseThrow(Exception::new);
        Order order = orderService.findOrderById(orderId).orElseThrow(Exception::new);
        driver.getOrders().add(order);
        order.setDriver(driver);
        driverService.saveDriver(driver);
        return "redirect:/order/orders";
    }
}
