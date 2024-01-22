package com.maquinaria.ecommerce.backend.infrastructure.adapter;

import com.maquinaria.ecommerce.backend.domain.model.Category;
import com.maquinaria.ecommerce.backend.domain.port.ICategoryRepository;
import com.maquinaria.ecommerce.backend.infrastructure.mapper.ICategoryMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryCrudRepositoryImpl implements ICategoryRepository {

    private final ICategoryCrudRepository categoryCrudRepository;
    private final ICategoryMapper categoryMapper;

    public CategoryCrudRepositoryImpl(ICategoryCrudRepository categoryCrudRepository, ICategoryMapper categoryMapper) {
        this.categoryCrudRepository = categoryCrudRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public Category save(Category category) {
        return categoryMapper.toCategory(categoryCrudRepository.save(categoryMapper.toCategoryEntity(category))) ;
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryMapper.toCategoryList(categoryCrudRepository.findAll());
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.toCategory(categoryCrudRepository.findById(id).orElseThrow(()->new RuntimeException("Categoria con id :"+ id + " no existe")));
    }

    @Override
    public void delateById(Integer id) {

        categoryCrudRepository.findById(id).orElseThrow(()->new RuntimeException("Categoria con id :"+ id + " no existe"));
        categoryCrudRepository.deleteById(id);
    }
}
