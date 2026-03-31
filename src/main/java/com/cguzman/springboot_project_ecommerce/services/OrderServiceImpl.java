package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Dto.OrderDto;
import com.cguzman.springboot_project_ecommerce.entities.Dto.OrderItemDto;
import com.cguzman.springboot_project_ecommerce.entities.Order;
import com.cguzman.springboot_project_ecommerce.entities.OrderItem;
import com.cguzman.springboot_project_ecommerce.entities.User;
import com.cguzman.springboot_project_ecommerce.exceptions.RegistryNotFoundException;
import com.cguzman.springboot_project_ecommerce.repositories.OrderRepository;
import com.cguzman.springboot_project_ecommerce.repositories.ProductRepository;
import com.cguzman.springboot_project_ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    @Override
    public List<OrderDto> findAll() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(order -> {
            orderDtos.add(saveToOrderDto(order));
        });
        return orderDtos;
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDto findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            return saveToOrderDto(order.get());
        }
        throw new RegistryNotFoundException("No se encontró ninguna orden con ese id");
    }

    @Transactional
    @Override
    public OrderDto save(Order oder) {
        return saveToOrderDto(orderRepository.save(oder));
    }

    @Transactional
    public OrderDto saveOrderDto(OrderDto orderDto) {
        Order order = new Order();
        order.setTotal(orderDto.getTotal());
        order.setDate(orderDto.getDate());
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow(()-> new RegistryNotFoundException("usuario no encontrado"));
        order.setUser(user);
        orderDto.getItems().forEach(orderItemDto -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(orderItemDto.getPrice());
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setProduct(productRepository.findById(orderItemDto.getProductId()).orElseThrow(() -> new RegistryNotFoundException("No se encontro el producto")));
            order.getItems().add(orderItem);
            orderItem.setOrder(order);
        });
        orderRepository.save(order);
        return saveToOrderDto(order);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RegistryNotFoundException("No se encontró ninguna orden con ese id"));
        orderRepository.deleteById(order.getId());
    }

    @Transactional
    @Override
    public OrderDto update(Long id, Order order) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()){
            Order orderOld = optionalOrder.get();
            orderOld.setUser(orderOld.getUser());
            orderOld.setDate(order.getDate());
            orderOld.setTotal(order.getTotal());
            return saveToOrderDto(orderRepository.save(orderOld));
        }
        throw new RegistryNotFoundException("No se encontró ninguna orden con ese id");
    }


    public OrderDto saveToOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUser().getId());
        orderDto.setNameUser(order.getUser().getName());
        orderDto.setDate(order.getDate());
        orderDto.setTotal(order.getTotal());
        order.getItems().forEach(orderItem -> {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setId(orderItem.getId());
            orderItemDto.setPrice(orderItem.getPrice());
            orderItemDto.setProductId(orderItem.getProduct().getId());
            orderItemDto.setProductName(orderItem.getProduct().getName());
            orderItemDto.setQuantity(orderItem.getQuantity());
            orderDto.getItems().add(orderItemDto);
        });
        orderDto.setItems(orderDto.getItems());
        return orderDto;
    }
}
