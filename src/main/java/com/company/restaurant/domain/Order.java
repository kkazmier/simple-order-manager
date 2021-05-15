package com.company.restaurant.domain;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{
    @ManyToOne
    @JoinColumn(name="driverId", referencedColumnName = "id")
    private Driver driver;

    @Column
    private String foodType;
}
