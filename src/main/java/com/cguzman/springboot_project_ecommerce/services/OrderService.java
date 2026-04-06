package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Dto.OrderDto;
import com.cguzman.springboot_project_ecommerce.entities.Order;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface OrderService extends BaseService<OrderDto, Order> {
    OrderDto saveOrderDto(OrderDto orderDto);
    OrderDto updateOrderDto(Long id, OrderDto orderDto);
    OrderDto updateOrder(Long id);
    List<OrderDto> findByUser(Long id);
}
