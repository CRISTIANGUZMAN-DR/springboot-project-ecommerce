package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{
    @Override
    public List<OrderItem> findAll() {
        return List.of();
    }

    @Override
    public OrderItem findById(Long id) {
        return null;
    }

    @Override
    public OrderItem save(OrderItem obj) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public OrderItem update(Long id, OrderItem obj) {
        return null;
    }
}
