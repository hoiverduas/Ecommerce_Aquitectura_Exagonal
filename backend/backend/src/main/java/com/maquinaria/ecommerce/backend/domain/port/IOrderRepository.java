package com.maquinaria.ecommerce.backend.domain.port;

import com.maquinaria.ecommerce.backend.domain.model.Order;


public interface IOrderRepository {

    Order save(Order order);
    Iterable<Order> findAll();
    Iterable<Order> findByUserId(Integer userId);
    Order findById(Integer id);
    void updateStateById(Integer id,String status);
}
