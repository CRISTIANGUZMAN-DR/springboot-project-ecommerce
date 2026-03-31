package com.cguzman.springboot_project_ecommerce.entities.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {
    @NotNull(message = "No puede estar nulo")
    @NotBlank(message = "No puede estar vacio")
    private String name;

    @NotNull(message = "No puede estar nulo")
    private Integer stock;

    @NotNull(message = "No puede estar nulo")
    @Min(100)
    private BigDecimal price;
    private List<Long> categoryIds;

    public ProductDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
