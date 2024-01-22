package com.maquinaria.ecommerce.backend.infrastructure.adapter;

import com.maquinaria.ecommerce.backend.domain.model.OrderStatus;
import com.maquinaria.ecommerce.backend.infrastructure.entity.OrderEntity;
import com.maquinaria.ecommerce.backend.infrastructure.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface IOrderCrudRepository extends CrudRepository<OrderEntity,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity o SET o.orderStatus = :status WHERE o.id = :id ")
    void updateStateById(Integer id, OrderStatus status);

    Iterable<OrderEntity> findByUserEntity(UserEntity userEntity);

}
