package com.company.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "drivers")
@Getter
@Setter
public class Driver extends Employee{
    @JsonManagedReference
    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "driver",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Order> orders;
}
