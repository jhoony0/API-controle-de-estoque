package api_controle_estoque.service;

import api_controle_estoque.model.Category;
import api_controle_estoque.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Category category;


    public List<Category> listCategory(){
        return categoryRepository.findAll(Sort.by("name").ascending());
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category searchCategory(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
