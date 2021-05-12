package com.company.restaurant.domain;

import javax.persistence.Column;

public class Employee extends BaseEntity{
    @Column
    private String firstName;

    @Column
    private String lastName;
}
