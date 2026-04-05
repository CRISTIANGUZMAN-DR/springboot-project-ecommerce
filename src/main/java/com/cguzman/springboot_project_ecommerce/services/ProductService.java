package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Dto.ProductDto;
import com.cguzman.springboot_project_ecommerce.entities.Product;

public interface ProductService extends BaseService<Product, Product>{
    Product addCategory(Long productId, Long categoryId);
    Product saveWithCategories(ProductDto productDto);
    ProductDto saveProductDto(Product product);
}
