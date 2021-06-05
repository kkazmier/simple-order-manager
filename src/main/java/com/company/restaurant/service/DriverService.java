package com.company.restaurant.service;

import com.company.restaurant.domain.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    Driver saveDriver(Driver driver);
    List<Driver> getAllDrivers();
    Optional<Driver> findDriverById(Long id) throws Exception;
    void deleteDriverById(Long id);
}
