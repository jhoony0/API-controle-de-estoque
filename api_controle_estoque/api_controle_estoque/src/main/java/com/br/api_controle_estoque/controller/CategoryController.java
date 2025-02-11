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



}
