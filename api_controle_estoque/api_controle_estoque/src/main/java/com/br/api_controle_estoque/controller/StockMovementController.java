package com.br.api_controle_estoque.controller;

import com.br.api_controle_estoque.DTO.StockMovementRequestDto;
import com.br.api_controle_estoque.DTO.StockMovementResponseDto;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stockMovement")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;



    @PostMapping
    public ResponseEntity<StockMovementResponseDto> createStockMovement(
            @Valid @RequestBody StockMovementRequestDto requestDto){

        StockMovement savedStockMovement = stockMovementService.createStockMovement(requestDto);
        StockMovementResponseDto responseDto = new StockMovementResponseDto(savedStockMovement);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    @GetMapping("/list")
    public List<StockMovementResponseDto> listMovements(){
        return stockMovementService.listStockMovement().stream()
                .map(StockMovementResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovementResponseDto> findMovements(@PathVariable Long id){
        StockMovement findMovement = stockMovementService.searchStockMovement(id);

        if (findMovement == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(StockMovementResponseDto.fromEntity(findMovement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockMovement> updateMovement(@PathVariable Long id,
                                                 @Valid @RequestBody StockMovement movement){

        StockMovement existingMovement = stockMovementService.searchStockMovement(id);


        if (existingMovement != null){
            existingMovement.setMovementType(movement.getMovementType());
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
