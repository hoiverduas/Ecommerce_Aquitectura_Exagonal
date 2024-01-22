package com.maquinaria.ecommerce.backend.infrastructure.adapter;

import com.maquinaria.ecommerce.backend.infrastructure.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface IProductCrudRepository extends CrudRepository<ProductEntity,Integer> {
}
