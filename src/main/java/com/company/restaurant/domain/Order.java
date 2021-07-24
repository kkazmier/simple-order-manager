package com.company.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    @JsonBackReference
    private Address address;

    @Column(name = "food_type")
    private String foodType;

    @Column(name = "status")
    private OrderStatus status;

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
    @JsonBackReference
    @JoinColumn(name="driverId", referencedColumnName = "id")
    private Driver driver;

    public Long secondsToDelivery(){
        return LocalTime.now().until(timeToDelivery, ChronoUnit.SECONDS);
    }
}
