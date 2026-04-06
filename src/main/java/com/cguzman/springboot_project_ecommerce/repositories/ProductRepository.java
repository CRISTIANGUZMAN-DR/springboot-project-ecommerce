package com.cguzman.springboot_project_ecommerce.repositories;

import com.cguzman.springboot_project_ecommerce.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByName(String name);

    @Query("select p from Product p where p.price < ?1")
    List<Product> findByPrice(Integer price);
}
