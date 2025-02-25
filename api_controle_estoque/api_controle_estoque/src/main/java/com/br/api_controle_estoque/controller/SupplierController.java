package com.br.api_controle_estoque.controller;

import com.br.api_controle_estoque.model.Supplier;
import com.br.api_controle_estoque.service.SupplierService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody Supplier supplier){
        Supplier savedSupplier = supplierService.saveSupplier(supplier);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/list")
    public List<Supplier> listSupplier(){
        return supplierService.listSupplier();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> searchSupplier (@PathVariable Long id){
        Supplier findSupplier = supplierService.searchSupplier(id);

        if( findSupplier == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findSupplier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id,
                                                   @Valid @RequestBody Supplier supplier){
        Supplier existingSupplier = supplierService.searchSupplier(id);

        if (existingSupplier != null){
            existingSupplier.setName(supplier.getName());
            existingSupplier.setAddress(supplier.getAddress());
            existingSupplier.setEmail(supplier.getEmail());
            existingSupplier.setPhone(supplier.getPhone());
            existingSupplier.setProducts(supplier.getProducts());
            return ResponseEntity.ok(supplierService.saveSupplier(existingSupplier));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id){
        Supplier existingSupplier = supplierService.searchSupplier(id);

        if(existingSupplier != null){
            supplierService.deleteSupplier(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }
}
