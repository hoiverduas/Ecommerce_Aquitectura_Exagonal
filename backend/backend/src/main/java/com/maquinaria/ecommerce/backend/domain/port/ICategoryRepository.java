package com.maquinaria.ecommerce.backend.domain.port;

import com.maquinaria.ecommerce.backend.domain.model.Category;
import com.maquinaria.ecommerce.backend.domain.model.User;

public interface ICategoryRepository {

    Category save(Category category);
    Iterable<Category> findAll();
    Category findById(Integer id);
    void delateById(Integer id);
}
