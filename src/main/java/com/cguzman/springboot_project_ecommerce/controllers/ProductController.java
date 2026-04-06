package com.cguzman.springboot_project_ecommerce.controllers;

import com.cguzman.springboot_project_ecommerce.entities.Dto.ProductDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(service.findById(id));
    }

    @GetMapping(params = "name")
    public ResponseEntity<Product> findByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(service.findByName(name));
    }

    @GetMapping(params = "price")
    public ResponseEntity<List<Product>> findByPrice(@RequestParam Integer price){
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(service.findByPrice(price));
    }

    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(service.saveWithCategories(productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product){
        return ResponseEntity.ok(service.update(id, product));
    }

    @PatchMapping("/{productId}/category/{categoryId}")
    public ResponseEntity<?> addCategory(@PathVariable Long productId, @PathVariable Long categoryId){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(service.addCategory(productId, categoryId));
    }
}
