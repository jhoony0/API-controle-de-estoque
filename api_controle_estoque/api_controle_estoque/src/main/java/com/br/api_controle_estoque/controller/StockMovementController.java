package com.br.api_controle_estoque.controller;

import com.br.api_controle_estoque.model.StockMovement;
import com.br.api_controle_estoque.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/stockMovement")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;


    @PostMapping
    public ResponseEntity<StockMovement> createStockMovement(@Valid @RequestBody StockMovement stockMovement){
        StockMovement savedStockMovement = stockMovementService.saveStockMovement(stockMovement);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/list")
    public List<StockMovement> listMovements(){
        return stockMovementService.listStockMovement();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovement> findMovements(@PathVariable Long id){
        StockMovement findMovement = stockMovementService.searchStockMovement(id);

        if (findMovement == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(findMovement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockMovement> updateMovement(@PathVariable Long id,
                                                 @Valid @RequestBody StockMovement movement){
        StockMovement existingMovement = stockMovementService.searchStockMovement(id);

        if (existingMovement != null){
            existingMovement.setMovement_type(movement.getMovement_type());
            existingMovement.setQuantity(movement.getQuantity());
            existingMovement.setObservation(movement.getObservation());
            existingMovement.setProduct(movement.getProduct());
            existingMovement.setUser(movement.getUser());
            return ResponseEntity.ok(stockMovementService.saveStockMovement(existingMovement));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        StockMovement existingMovement = stockMovementService.searchStockMovement(id);
        if (existingMovement != null){
            stockMovementService.deleteStockMovement(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }
}
