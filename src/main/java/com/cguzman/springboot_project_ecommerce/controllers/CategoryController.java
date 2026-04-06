package com.cguzman.springboot_project_ecommerce.controllers;

import com.cguzman.springboot_project_ecommerce.entities.Category;
import com.cguzman.springboot_project_ecommerce.services.CategoryService;
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
        return ResponseEntity.status(HttpStatus.OK.value()).body(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK.value()).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(service.save(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @Valid @RequestBody Category category){
        return ResponseEntity.ok(service.update(id, category));
    }
}
