package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Category;
import com.cguzman.springboot_project_ecommerce.entities.Dto.ProductDto;
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

    @Transactional(readOnly = true)
    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name).orElseThrow(()-> new RegistryNotFoundException("No se encontro el producto"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findByPrice(Integer price) {
        return productRepository.findByPrice(price);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product saveWithCategories(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        if (productDto.getCategoryIds() != null && !productDto.getCategoryIds().isEmpty()){
            productDto.getCategoryIds().forEach(id ->{
                Category category = categoryRepository.findById(id).orElseThrow(() -> new RegistryNotFoundException("No se encontró una categoria con el id: " + id));
                product.getCategories().add(category);
            });
        };
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
    public Product addCategory(Long productId, Long categoryId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new RegistryNotFoundException("Producto no encontrado"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new RegistryNotFoundException("Categoria no encontrada"));

        product.getCategories().add(category);

        return productRepository.save(product);
    }

    @Override
    public ProductDto saveProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
