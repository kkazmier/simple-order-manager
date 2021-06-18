package com.company.restaurant.repository;

import com.company.restaurant.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AddressRepository extends CrudRepository<Address, Long> {
    Address save(Address address);
    List<Address> findAll();
    Optional<Address> findById(Long id);
    void deleteById(Long id);
}
