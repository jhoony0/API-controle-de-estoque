package api_controle_estoque.service;

import api_controle_estoque.model.Supplier;
import api_controle_estoque.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private Supplier supplier;

    public List<Supplier> listSupplier(){
        return supplierRepository.findAll();
    }

    public Supplier saveSupplier(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public Supplier searchSupplier(Long id){
        return supplierRepository.findById(id).orElse(null);
    }

    public void deleteSupplier(Long id){
        supplierRepository.deleteById(id);
    }
}
