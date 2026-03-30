package com.cguzman.springboot_project_ecommerce.controllers;

import com.cguzman.springboot_project_ecommerce.entities.Dto.OrderDto;
import com.cguzman.springboot_project_ecommerce.entities.Order;
import com.cguzman.springboot_project_ecommerce.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerce/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAll(){
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderDto> save(@Valid @RequestBody Order order){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(service.save(order));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(Long id){
        service.deleteById(id);
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<OrderDto> update(Long id, @Valid @RequestBody Order order){
        return ResponseEntity.ok(service.update(id, order));
    }

}
