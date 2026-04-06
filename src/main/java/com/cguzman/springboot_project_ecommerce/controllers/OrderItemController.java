package com.cguzman.springboot_project_ecommerce.controllers;

import com.cguzman.springboot_project_ecommerce.entities.Dto.OrderItemDto;
import com.cguzman.springboot_project_ecommerce.entities.OrderItem;
import com.cguzman.springboot_project_ecommerce.services.OrderItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerce/items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> findAll(){
        return ResponseEntity.ok(orderItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK.value()).body(orderItemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> save(@Valid @RequestBody OrderItemDto orderItemDto){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(orderItemService.saveOrderItemDto(orderItemDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> update(@PathVariable Long id, @Valid @RequestBody OrderItemDto orderItemDto){
        return ResponseEntity.ok(orderItemService.updateOrderItemDto(id, orderItemDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        orderItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
