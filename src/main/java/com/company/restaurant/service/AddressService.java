package com.company.restaurant.service;

import com.company.restaurant.domain.Address;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Address save(Address address);
    List<Address> findAll();
    Optional<Address> findById(Long id);
    void deleteById(Long id);
    void setGeographicalCoords(Long id) throws Exception;
    Address setGeographicalCoords(Address address) throws JsonProcessingException;
}
