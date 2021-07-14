package com.company.restaurant.controller;

import com.company.restaurant.service.AddressServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("address")
public class AddressController {
    private final AddressServiceImpl addressService;

    @GetMapping("setGeographicalCoordinates/{id}")
    public void setGeographicalCoordinates(@PathVariable("id") Long id) {

    }
}
