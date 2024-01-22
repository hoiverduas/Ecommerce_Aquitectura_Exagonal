package com.maquinaria.ecommerce.backend.infrastructure.adapter;

import com.maquinaria.ecommerce.backend.infrastructure.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryCrudRepository extends CrudRepository<CategoryEntity,Integer> {
}
