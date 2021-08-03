package com.company.restaurant.service;

import com.company.restaurant.domain.Address;
import com.company.restaurant.domain.Driver;
import com.company.restaurant.domain.Order;
import com.company.restaurant.domain.OrderPoint;
import com.company.restaurant.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderPointServiceImpl implements OrderPointService{
    private final Logger logger = LoggerFactory.getLogger(OrderPointServiceImpl.class);
    private final OrderRepository orderRepository;
    private final AddressService addressService;

    public List<OrderPoint> getAllOrderPoints() {
        List<Order> orders = orderRepository.findAll();
        List<OrderPoint> orderPoints = new LinkedList<>();
        orders.stream().forEach(order -> {
            OrderPoint orderPoint = new OrderPoint();
            Address address = order.getAddress();
            logger.info(address.toString());
            if(address.getLongitude() == null || address.getLatitude() == null){
                try {
                    address = addressService.setGeographicalCoords(address);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            Driver driver = order.getDriver();
            if (driver == null){
                driver = new Driver();
            }
            logger.info(driver.toString());

            orderPoint.setLatitude(address.getLatitude());
            orderPoint.setLongitude(address.getLongitude());
            orderPoint.setStreet(address.getStreet());
            orderPoint.setBuildingNumber(address.getBuildingNumber());
            orderPoint.setSecondsToDelivery(order.getSecondsToDelivery());
            orderPoint.setDriverFirstName(driver.getFirstName());
            orderPoint.setDriverLastName(driver.getLastName());
            orderPoint.setStatus(order.getStatus());
            orderPoint.setFoodType(order.getFoodType());
            logger.info(orderPoint.toString());
            orderPoints.add(orderPoint);
        });
        return orderPoints;
    }
}
