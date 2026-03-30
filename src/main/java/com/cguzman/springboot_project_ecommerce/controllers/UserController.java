package com.cguzman.springboot_project_ecommerce.controllers;


import com.cguzman.springboot_project_ecommerce.entities.Dto.UserDto;
import com.cguzman.springboot_project_ecommerce.entities.User;
import com.cguzman.springboot_project_ecommerce.services.BaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ecommerce/users")
public class UserController {

    @Autowired
    private BaseService<UserDto, User> service;

    @GetMapping
    public List<UserDto> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
       return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(service.save(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody User user){
        return ResponseEntity.ok(service.update(id, user));
    }

}
