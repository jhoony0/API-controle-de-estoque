package com.br.api_controle_estoque.controller;

import com.br.api_controle_estoque.DTO.ProductResponseDTO;
import com.br.api_controle_estoque.model.Product;
import com.br.api_controle_estoque.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);

        // Build the destination URI for the newly created product. To return the HTTP status code 201(created).
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedProduct);
    }

    @GetMapping("/list")
    public List<Product> listProducts(){
        return productService.listProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findProducts(@PathVariable Long id){
        Product findProduct = productService.searchProduct(id);

        if (findProduct == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(findProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @Valid @RequestBody Product product){
        Product existingProduct = productService.searchProduct(id);

        if (existingProduct != null){
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setSupplier(product.getSupplier());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setStockMovements(product.getStockMovements());
            existingProduct.setUser(product.getUser());
            return ResponseEntity.ok(productService.saveProduct(existingProduct));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        Product findProduct = productService.searchProduct(id);
        if (findProduct != null){
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }
}
