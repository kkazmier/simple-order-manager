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

    @Column(name = "town")
    String town;

    @Column(name = "postalCode")
    String postalCode;

    @Column(name = "latitude")
    String latitude;

    @Column(name = "longitude")
    String longitude;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Order order;

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", flatNumber='" + flatNumber + '\'' +
                ", floor='" + floor + '\'' +
                ", town='" + town + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", order=" + order +
                '}';
    }
}
