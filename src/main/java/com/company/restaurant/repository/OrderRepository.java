package com.company.restaurant.repository;

import com.company.restaurant.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface OrderRepository extends CrudRepository<Order, Long> {
    Order save(Order order);
    List<Order> findAll();
    Optional<Order> findById(Long id);
    void deleteById(Long id);
}
