package com.br.api_controle_estoque.service;

import com.br.api_controle_estoque.DTO.StockMovementRequestDto;
import com.br.api_controle_estoque.exceptions.NotFoundException;
import com.br.api_controle_estoque.model.MovementType;
import com.br.api_controle_estoque.model.Product;
import com.br.api_controle_estoque.model.StockMovement;
import com.br.api_controle_estoque.model.User;
import com.br.api_controle_estoque.repository.ProductRepository;
import com.br.api_controle_estoque.repository.StockMovementRepository;
import com.br.api_controle_estoque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;



    public List<StockMovement> listStockMovement(){
        return stockMovementRepository.findAll();
    }

    public StockMovement saveStockMovement(StockMovement stockMovement){


        if (stockMovement.getId() == null){
            stockMovement.setMovement_date(LocalDateTime.now());
        }
        return stockMovementRepository.save(stockMovement);
    }
    public StockMovement createStockMovement(StockMovementRequestDto requestDto) {


        Product product = productRepository.findById(requestDto.productId())
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        User user = userRepository.findById(requestDto.userId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        // Criar a movimentação de estoque
        StockMovement stockMovement = new StockMovement();
        stockMovement.setProduct(product);
        stockMovement.setMovementType(requestDto.movementType());
        stockMovement.setMovement_date(LocalDateTime.now());
        stockMovement.setQuantity(requestDto.quantity());
        stockMovement.setObservation(requestDto.observation());
        stockMovement.setUser(user);

        // Lógica para atualizar na tabela de produtos
        if (stockMovement.getMovementType() == MovementType.ENTRADA) {
            product.setQuantity(product.getQuantity() + stockMovement.getQuantity());
        } else if (stockMovement.getMovementType() == MovementType.SAIDA) {
            if (product.getQuantity() < stockMovement.getQuantity()) {
                throw new RuntimeException("Quantidade insuficiente em estoque");
            }
            product.setQuantity(product.getQuantity() - stockMovement.getQuantity());
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
