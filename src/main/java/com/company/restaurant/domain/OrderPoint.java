package com.company.restaurant.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPoint {
    private String longitude;
    private String latitude;
    private String street;
    private String buildingNumber;
    private Integer secondsToDelivery;
    private String driverFirstName;
    private String driverLastName;
    private OrderStatus status;
    private String foodType;

    @Override
    public String toString() {
        return "OrderPoint{" +
                "longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", secondsToDelivery=" + secondsToDelivery +
                ", driverFirstName='" + driverFirstName + '\'' +
                ", driverLastName='" + driverLastName + '\'' +
                ", status=" + status +
                ", foodType='" + foodType + '\'' +
                '}';
    }
}
