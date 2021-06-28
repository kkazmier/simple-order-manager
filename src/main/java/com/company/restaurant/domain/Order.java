package com.company.restaurant.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;

    @Column(name = "food_type")
    private String foodType;

    @Column(name = "status")
    private String status;

    @Column(name = "createTime")
    private LocalTime createTime;

    @Column(name = "timeToDelivery")
    private LocalTime timeToDelivery;

    @Column(name = "secondsToDelivery")
    private Integer secondsToDelivery;

    @Column(name = "closeTime")
    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    private LocalTime closeTime;

    @ManyToOne
    @JoinColumn(name="driverId", referencedColumnName = "id")
    private Driver driver;
}
