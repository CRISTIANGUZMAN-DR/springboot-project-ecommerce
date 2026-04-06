package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Dto.OrderDto;
import com.cguzman.springboot_project_ecommerce.entities.Dto.OrderItemDto;
import com.cguzman.springboot_project_ecommerce.entities.Order;
import com.cguzman.springboot_project_ecommerce.entities.OrderItem;
import com.cguzman.springboot_project_ecommerce.entities.User;
import com.cguzman.springboot_project_ecommerce.exceptions.RegistryNotFoundException;
import com.cguzman.springboot_project_ecommerce.repositories.OrderItemRepository;
import com.cguzman.springboot_project_ecommerce.repositories.OrderRepository;
import com.cguzman.springboot_project_ecommerce.repositories.ProductRepository;
import com.cguzman.springboot_project_ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

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

    @Transactional(readOnly = true)
    @Override
    public List<OrderDto> findByUser(Long id) {
        List<Order> orders = orderRepository.findByUserId(id);
        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(order -> {
            orderDtos.add(saveToOrderDto(order));
        });
        return orderDtos;
    }

    @Transactional
    @Override
    public OrderDto save(Order oder) {
        return saveToOrderDto(orderRepository.save(oder));
    }

    @Transactional
    @Override
    public OrderDto saveOrderDto(OrderDto orderDto) {
        Order order = new Order();
        order.setDate(orderDto.getDate());
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow(()-> new RegistryNotFoundException("usuario no encontrado"));
        order.setUser(user);
        orderDto.getItems().forEach(orderItemDto -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(orderItemDto.getPrice());
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setProduct(productRepository.findById(orderItemDto.getProductId()).orElseThrow(() -> new RegistryNotFoundException("No se encontro el producto")));
            orderItem.calculatedPrice(orderItem);
            order.getItems().add(orderItem);
            orderItem.setOrder(order);
        });
        order.calcularTotalOrder(order.getItems());
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

    @Transactional
    @Override
    public OrderDto updateOrderDto(Long id, OrderDto orderDto) {
            Order order = orderRepository.findById(id).orElseThrow(()-> new RegistryNotFoundException("No se encontró ninguna orden con ese id"));
            order.setUser(userRepository.findById(orderDto.getUserId()).orElseThrow(()-> new RegistryNotFoundException("Usuario no encontrado")));
            order.setDate(orderDto.getDate());

            order.getItems().clear();

            orderDto.getItems().forEach(orderItemDto -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(orderItemDto.getId());
                orderItem.setQuantity(orderItemDto.getQuantity());
                orderItem.setPrice(orderItemDto.getPrice());
                orderItem.setProduct(productRepository.findById(orderItemDto.getProductId()).orElseThrow(()-> new RegistryNotFoundException("Producto no encontrado")));
                orderItem.calculatedPrice(orderItem);
                orderItem.setOrder(order);
                order.getItems().add(orderItem);
            });
            order.calcularTotalOrder(order.getItems());
            return saveToOrderDto(orderRepository.save(order));
    }

    @Transactional
    @Override
    public OrderDto updateOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.getItems().forEach(orderItem -> {
            orderItem.calculatedPrice(orderItem);
            orderItemRepository.save(orderItem);
        });
        order.calcularTotalOrder(order.getItems());
        orderRepository.save(order);
        return saveToOrderDto(order);
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
            orderItemDto.setProductDto(productService.saveProductDto(orderItem.getProduct()));
            orderItemDto.setQuantity(orderItem.getQuantity());
            orderItemDto.setOrderId(order.getId());
            orderDto.getItems().add(orderItemDto);
        });
        orderDto.setItems(orderDto.getItems());
        return orderDto;
    }
}
