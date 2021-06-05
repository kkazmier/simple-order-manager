package com.company.restaurant.repository;

import com.company.restaurant.domain.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface DriverRepository extends CrudRepository<Driver, Long> {
    Driver save(Driver driver);
    List<Driver> findAll();
    Optional<Driver> findById(Long id);
    void deleteById(Long id);
}
