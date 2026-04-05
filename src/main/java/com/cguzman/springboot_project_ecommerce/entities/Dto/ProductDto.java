package com.cguzman.springboot_project_ecommerce.entities.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {

    private Long id;

    @NotNull(message = "No puede estar nulo")
    @NotBlank(message = "No puede estar vacio")
    private String name;

    @JsonIgnore
    @NotNull(message = "No puede estar nulo")
    private Integer stock;

    @NotNull(message = "No puede estar nulo")
    @Min(100)
    private BigDecimal price;

    @JsonIgnore
    private List<Long> categoryIds;

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
