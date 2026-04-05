package com.cguzman.springboot_project_ecommerce.controllers;


import com.cguzman.springboot_project_ecommerce.entities.Dto.UserDto;
import com.cguzman.springboot_project_ecommerce.entities.User;
import com.cguzman.springboot_project_ecommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerce/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserDto> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
       return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(params = "email")
    public ResponseEntity<UserDto> findByEmail(@RequestParam String email){
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(service.findByEmail(email));
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<UserDto>> findByNameContaining(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(service.findByNameContaining(name));
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

    @PatchMapping("/{id}/active/{active}")
    public ResponseEntity<?> activate(@PathVariable Long id, @PathVariable boolean active){
        service.activate(id, active);
        return ResponseEntity.ok().build();
    }

}
