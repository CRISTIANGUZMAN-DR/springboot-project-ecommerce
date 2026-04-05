package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Dto.OrderItemDto;
import com.cguzman.springboot_project_ecommerce.entities.OrderItem;
import org.jspecify.annotations.Nullable;

public interface OrderItemService extends BaseService<OrderItemDto, OrderItem> {
    OrderItemDto saveOrderItemDto(OrderItemDto orderItemDto);

    @Nullable OrderItemDto updateOrderItemDto(Long id, OrderItemDto orderItemDto);
}
