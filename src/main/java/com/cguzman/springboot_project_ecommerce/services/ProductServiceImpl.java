package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Category;
import com.cguzman.springboot_project_ecommerce.entities.Product;
import com.cguzman.springboot_project_ecommerce.exceptions.RegistryNotFoundException;
import com.cguzman.springboot_project_ecommerce.repositories.CategoryRepository;
import com.cguzman.springboot_project_ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new RegistryNotFoundException("No se encontró el producto con ese id"));
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        this.findById(id);
        productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Product update(Long id, Product product) {
        Product productExistente = this.findById(id);
        productExistente.setName(product.getName());
        productExistente.setStock(product.getStock());
        productExistente.setPrice(product.getPrice());
        return productRepository.save(productExistente);
    }

    @Transactional
    @Override
    public Product saveCategoryProduct(Product product) {
        return null;
    }

    /*@Transactional
    @Override
    public Product saveCategoryProduct(Product product){
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        Optional<Category> optionalCategory = categoryRepository.findById(product.getCategories().);
        optionalProduct.ifPresent(product1 -> {
            categoryRepository
        });
    }*/
}
