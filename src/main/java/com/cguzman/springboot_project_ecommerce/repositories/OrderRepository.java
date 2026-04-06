package com.cguzman.springboot_project_ecommerce.repositories;

import com.cguzman.springboot_project_ecommerce.entities.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserId(Long id);
}
