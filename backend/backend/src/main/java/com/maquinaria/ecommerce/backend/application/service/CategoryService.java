package com.maquinaria.ecommerce.backend.application.service;

import com.maquinaria.ecommerce.backend.domain.model.Category;
import com.maquinaria.ecommerce.backend.domain.model.User;
import com.maquinaria.ecommerce.backend.domain.port.ICategoryRepository;

public class CategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category){
        return this.categoryRepository.save(category);
    }

    public Iterable<Category> findAll(){
        return this.categoryRepository.findAll();
    }

    public Category findById(Integer id){
        return this.categoryRepository.findById(id);
    }

    public void deleteById(Integer id){
        this.categoryRepository.delateById(id);
    }
}
