package com.cguzman.springboot_project_ecommerce.services;

import java.util.List;

public interface BaseService<T, E> {
    List<T> findAll();
    T findById(Long id);
    T save(E obj);
    void deleteById(Long id);
    T update(Long id,  E obj);
}
