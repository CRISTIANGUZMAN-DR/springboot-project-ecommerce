package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Dto.ProductDto;
import com.cguzman.springboot_project_ecommerce.entities.Product;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProductService extends BaseService<Product, Product>{
    Product addCategory(Long productId, Long categoryId);
    Product saveWithCategories(ProductDto productDto);
    ProductDto saveProductDto(Product product);
    Product findByName(String name);
    List<Product> findByPrice(Integer price);
}
