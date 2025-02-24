package com.br.api_controle_estoque.DTO;

import com.br.api_controle_estoque.model.Product;

import java.math.BigDecimal;

public record ProductResponseDTO(Long id,
                                 String name,
                                 String description,
                                 BigDecimal price,
                                 Integer quantity,
                                 String categoryName,
                                 String supplierName,
                                 String userName) {


    public ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory() != null ? product.getCategory().getName() : null, // Nome da categoria
                product.getSupplier() != null ? product.getSupplier().getName() : null, // Nome do fornecedor
                product.getUser() != null ? product.getUser().getName() : null          // Nome do usuário
        );

    }
}
