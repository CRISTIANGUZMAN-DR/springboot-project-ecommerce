package com.cguzman.springboot_project_ecommerce.repositories;

import com.cguzman.springboot_project_ecommerce.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
