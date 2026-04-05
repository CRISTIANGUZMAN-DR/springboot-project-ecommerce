package com.cguzman.springboot_project_ecommerce.repositories;

import com.cguzman.springboot_project_ecommerce.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByNameContaining(String name);
}
