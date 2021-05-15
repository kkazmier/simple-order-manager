package com.company.restaurant.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Driver extends Employee{
    @OneToMany(mappedBy="driver")
    private Set<Order> orders;
}
