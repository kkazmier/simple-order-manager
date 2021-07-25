package com.company.restaurant.controller;

import com.company.restaurant.domain.Address;
import com.company.restaurant.domain.OrderPoint;
import com.company.restaurant.domain.OrderStatus;
import com.company.restaurant.service.AddressServiceImpl;
import com.company.restaurant.service.OrderPointServiceImpl;
import com.company.restaurant.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("streetMap")
public class StreetMapController {
    private final Logger logger = LoggerFactory.getLogger(StreetMapController.class);
    private final AddressServiceImpl addressService;
    private final OrderServiceImpl orderService;
    private final OrderPointServiceImpl orderPointService;

    @ModelAttribute("allOrderPoints")
    public List<OrderPoint> getAllOrderPoints(){
        List<OrderPoint> orderPoints = orderPointService.getAllOrderPoints();
        logger.info("Get order points: " + orderPoints.size());
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
        logger.info("Get order points: " + orderPoints.size());
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
                logger.info(address.toString());
            }
        });
        return addresses;
    }



    @GetMapping("map")
    public String showMap(){
        return "streetMap.html";
    }
}
