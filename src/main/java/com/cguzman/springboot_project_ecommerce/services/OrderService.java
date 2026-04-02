package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Dto.OrderDto;
import com.cguzman.springboot_project_ecommerce.entities.Order;

public interface OrderService extends BaseService<OrderDto, Order> {
    OrderDto saveOrderDto(OrderDto orderDto);
    OrderDto updateOrderDto(Long id, OrderDto orderDto);
}
