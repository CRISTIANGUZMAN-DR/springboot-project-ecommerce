package com.cguzman.springboot_project_ecommerce.repositories;

import com.cguzman.springboot_project_ecommerce.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
