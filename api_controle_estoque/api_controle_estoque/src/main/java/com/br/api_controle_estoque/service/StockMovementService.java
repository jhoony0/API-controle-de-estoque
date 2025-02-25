package com.br.api_controle_estoque.service;

import com.br.api_controle_estoque.exceptions.NotFoundException;
import com.br.api_controle_estoque.model.StockMovement;
import com.br.api_controle_estoque.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;



    public List<StockMovement> listStockMovement(){
        return stockMovementRepository.findAll();
    }

    public StockMovement saveStockMovement(StockMovement stockMovement){
        if (stockMovement.getId() == null){
            stockMovement.setMovement_date(LocalDateTime.now());
        }
        return stockMovementRepository.save(stockMovement);
    }

    public StockMovement searchStockMovement(Long id){

        return stockMovementRepository.findById(id).orElseThrow( () -> new NotFoundException("Nenhuma movimentação encontrada com esse id"));
    }

    public void deleteStockMovement(Long id){
        stockMovementRepository.deleteById(id);
    }
}
