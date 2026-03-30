package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Product;

public interface ProductService extends BaseService<Product, Product>{
    Product saveCategoryProduct(Product product);
}
