package com.br.api_controle_estoque.controller;

import com.br.api_controle_estoque.model.Category;
import com.br.api_controle_estoque.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category){
       Category savedCategory = categoryService.saveCategory(category);

       // Contruir a URI de destino da nova categoria criada
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategory.getId())
                .toUri();
       return ResponseEntity.created(location).body(savedCategory);
    }


    @GetMapping("/list")
    public List<Category> listCategory(){
        return categoryService.listCategory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> searchCategory(@PathVariable Long id){
        Category findCategory = categoryService.searchCategory(id);
        if (findCategory == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,
                                                   @Valid @RequestBody Category category){

        Category existingCategory = categoryService.searchCategory(id);

        if (existingCategory != null){
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            existingCategory.setProducts(category.getProducts());
            return ResponseEntity.ok(categoryService.saveCategory(existingCategory));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        Category existingCategory = categoryService.searchCategory(id);
        if (existingCategory != null){
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
