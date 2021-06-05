package com.company.restaurant.service;

import com.company.restaurant.domain.Driver;
import com.company.restaurant.repository.DriverRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class DriverServiceImpl implements DriverService{
    private final DriverRepository driverRepository;

    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Optional<Driver> findDriverById(Long id) throws Exception {
        return driverRepository.findById(id);
    }

    public void deleteDriverById(Long id) {
        driverRepository.deleteById(id);
    }
}
