package com.br.api_controle_estoque.DTO;

import com.br.api_controle_estoque.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private LocalDateTime lastStockUpdate;
    private String nameCategory;
    private String namSupplier;
    private String nameUser;


    public ProductResponseDto(Long id, String name, String description, BigDecimal price, Integer quantity,
                              LocalDateTime lastStockUpdate, String nameCategory, String namSupplier, String nameUser) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.lastStockUpdate = lastStockUpdate;
        this.nameCategory = nameCategory;
        this.namSupplier = namSupplier;
        this.nameUser = nameUser;
    }

    public ProductResponseDto() {
    }

    public static ProductResponseDto fromEntity(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getLastStockUpdate(),
                product.getCategory() != null ? product.getCategory().getName() : null, // Nome da Categoria
                product.getSupplier() != null ? product.getSupplier().getName() : null, // Nome do Fornecedor
                product.getUser() != null ? product.getUser().getName() : null          // Nome do Usuário
        );

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLastStockUpdate() {
        return lastStockUpdate;
    }

    public void setLastStockUpdate(LocalDateTime lastStockUpdate) {
        this.lastStockUpdate = lastStockUpdate;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNamSupplier() {
        return namSupplier;
    }

    public void setNamSupplier(String namSupplier) {
        this.namSupplier = namSupplier;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
