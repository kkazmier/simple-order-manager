package com.company.restaurant.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address extends BaseEntity {
    @Column(name = "street")
    String street;

    @Column(name = "buildingNumber")
    String buildingNumber;

    @Column(name = "flatNumber")
    String flatNumber;

    @Column(name = "floor")
    String floor;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Order order;
}
