package com.cguzman.springboot_project_ecommerce.controllers;

import com.cguzman.springboot_project_ecommerce.entities.Product;
import com.cguzman.springboot_project_ecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerce/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(service.save(product));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(Long id){
        service.deleteById(id);
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Product> update(Long id, @Valid @RequestBody Product product){
        return ResponseEntity.ok(service.update(id, product));
    }

    @PostMapping("/category")
    public ResponseEntity<?> saveCategoryProduct(@RequestBody Product product){
        return null;
    }
}
