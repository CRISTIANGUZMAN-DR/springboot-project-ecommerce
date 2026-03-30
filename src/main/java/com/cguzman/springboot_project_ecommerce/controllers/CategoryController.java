package com.cguzman.springboot_project_ecommerce.controllers;

import com.cguzman.springboot_project_ecommerce.entities.Category;
import com.cguzman.springboot_project_ecommerce.entities.Product;
import com.cguzman.springboot_project_ecommerce.services.CategoryService;
import com.cguzman.springboot_project_ecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerce/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(service.save(category));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(Long id){
        service.deleteById(id);
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Category> update(Long id, @Valid @RequestBody Category category){
        return ResponseEntity.ok(service.update(id, category));
    }
}
