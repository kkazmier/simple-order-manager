package com.company.restaurant.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "drivers")
@Getter
@Setter
public class Driver extends Employee{
    @OneToMany(mappedBy="driver")
    private Set<Order> orders;
}
