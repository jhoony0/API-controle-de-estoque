package api_controle_estoque.service;

import api_controle_estoque.model.Product;
import api_controle_estoque.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Product product;


    public List<Product> listProduct(){
        return productRepository.findAll(Sort.by("name").ascending());
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product searchProduct(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
