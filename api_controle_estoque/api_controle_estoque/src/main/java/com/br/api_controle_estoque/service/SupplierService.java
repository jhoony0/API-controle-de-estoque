package com.br.api_controle_estoque.service;

import com.br.api_controle_estoque.exceptions.NotFoundException;
import com.br.api_controle_estoque.model.Supplier;
import com.br.api_controle_estoque.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;



    public List<Supplier> listSupplier(){
        return supplierRepository.findAll();
    }

    public Supplier saveSupplier(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public Supplier searchSupplier(Long id){
        return supplierRepository.findById(id).orElseThrow( () -> new NotFoundException("Nenhum fornecedor encontrado com esse id."));
    }

    public void deleteSupplier(Long id){
        supplierRepository.deleteById(id);
    }
}
