package com.company.restaurant.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name="driverId", referencedColumnName = "id")
    private Driver driver;
}
