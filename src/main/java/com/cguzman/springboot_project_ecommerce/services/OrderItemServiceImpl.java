package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Dto.OrderItemDto;
import com.cguzman.springboot_project_ecommerce.entities.Order;
import com.cguzman.springboot_project_ecommerce.entities.OrderItem;
import com.cguzman.springboot_project_ecommerce.entities.Product;
import com.cguzman.springboot_project_ecommerce.exceptions.RegistryNotFoundException;
import com.cguzman.springboot_project_ecommerce.repositories.OrderItemRepository;
import com.cguzman.springboot_project_ecommerce.repositories.OrderRepository;
import com.cguzman.springboot_project_ecommerce.repositories.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItemDto> findAll() {
        List<OrderItem> orderItems = (List<OrderItem>) orderItemRepository.findAll();
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        orderItems.forEach(item ->{
            orderItemDtos.add(saveToOrderItemDto(item));
        });
        return orderItemDtos;
    }

    @Override
    public OrderItemDto findById(Long id) {
        OrderItem item = orderItemRepository.findById(id).orElseThrow(()-> new RegistryNotFoundException("Item no encontrado"));
        return saveToOrderItemDto(item);
    }

    @Override
    public OrderItemDto save(OrderItem item) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(()-> new RegistryNotFoundException("Item no encontrado"));
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItemDto update(Long id, OrderItem item) {
        return null;
    }

    @Override
    public OrderItemDto saveOrderItemDto(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        Order order = orderRepository.findById(orderItemDto.getOrderId()).orElseThrow(()-> new RegistryNotFoundException("Orden no encontrada"));
        Product product = productRepository.findById(orderItemDto.getProductId()).orElseThrow(()-> new RegistryNotFoundException("Producto no encontrado"));
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.calculatedPrice(orderItem);

        order.getItems().add(orderItem);
        order.calcularTotalOrder(order.getItems());
        orderRepository.save(order);

        OrderItem orderItem1 = orderItemRepository.save(orderItem);
        return saveToOrderItemDto(orderItem1);
    }

    @Override
    public @Nullable OrderItemDto updateOrderItemDto(Long id, OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(()-> new RegistryNotFoundException("Item no encontrado"));
        Order order = orderRepository.findById(orderItemDto.getOrderId()).orElseThrow(()-> new RegistryNotFoundException("Orden no encontrada"));
        Product product = productRepository.findById(orderItemDto.getProductId()).orElseThrow(()-> new RegistryNotFoundException("Producto no encontrado"));
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.calculatedPrice(orderItem);
        OrderItem orderItem1 = orderItemRepository.save(orderItem);
        return saveToOrderItemDto(orderItem1);
    }

    private OrderItemDto saveToOrderItemDto(OrderItem item) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(item.getId());
        orderItemDto.setOrderId(item.getOrder().getId());
        orderItemDto.setProductDto(productService.saveProductDto(item.getProduct()));
        orderItemDto.setQuantity(item.getQuantity());
        orderItemDto.setPrice(item.getPrice());
        return orderItemDto;
    }
}
