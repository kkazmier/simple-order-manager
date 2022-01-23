package com.company.restaurant.controller;

import com.company.restaurant.domain.Address;
import com.company.restaurant.domain.OrderPoint;
import com.company.restaurant.domain.OrderStatus;
import com.company.restaurant.service.AddressServiceImpl;
import com.company.restaurant.service.OrderPointServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(MainPageController.class);
    private final AddressServiceImpl addressService;
    private final OrderPointServiceImpl orderPointService;

    @GetMapping("/")
    String mainPage() {
        return "index";
    }

    @ModelAttribute("allOrderPoints")
    public List<OrderPoint> getAllOrderPoints(){
        List<OrderPoint> orderPoints = orderPointService.getAllOrderPoints();
        logger.info("Get all order points.");
        return orderPoints;
    }

    @ModelAttribute("newOrderPoints")
    public List<OrderPoint> getNewOrderPoints(){
        List<OrderPoint> orderPoints = new ArrayList<>();
        orderPointService.getAllOrderPoints().stream().forEach(
                orderPoint -> {
                    if (orderPoint.getStatus().equals(OrderStatus.NEW)){
                        orderPoints.add(orderPoint);
                    }
                }
        );
        return orderPoints;
    }

    @ModelAttribute("assignedOrderPoints")
    public List<OrderPoint> getAssignedOrderPoints(){
        List<OrderPoint> orderPoints = new ArrayList<>();
        orderPointService.getAllOrderPoints().stream().forEach(
                orderPoint -> {
                    if (orderPoint.getStatus().equals(OrderStatus.ASSIGNED)){
                        orderPoints.add(orderPoint);
                    }
                }
        );
        return orderPoints;
    }

    @ModelAttribute("completedOrderPoints")
    public List<OrderPoint> getCompletedOrderPoints(){
        List<OrderPoint> orderPoints = new ArrayList<>();
        orderPointService.getAllOrderPoints().stream().forEach(
                orderPoint -> {
                    if (orderPoint.getStatus().equals(OrderStatus.COMPLETED)){
                        orderPoints.add(orderPoint);
                    }
                }
        );
        return orderPoints;
    }

    @ModelAttribute("allAddresses")
    public List<Address> getAllAddresses() {
        List<Address> addresses = addressService.findAll();
        addresses.stream().forEach(address -> {
            if(address.getLongitude() == null || address.getLatitude() == null){
                try {
                    addressService.setGeographicalCoords(address.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return addresses;
    }
}
