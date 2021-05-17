package com.company.restaurant.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity{
    @ManyToOne
    @JoinColumn(name="driver_id", referencedColumnName = "id")
    private Driver driver;

    @Column(name = "food_type")
    private String foodType;
}
