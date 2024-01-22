package com.maquinaria.ecommerce.backend.application.service;

import com.maquinaria.ecommerce.backend.domain.model.Order;
import com.maquinaria.ecommerce.backend.domain.port.IOrderRepository;

public class OrderService {

    private final IOrderRepository orderRepository;

    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public Order save(Order order){
        return this.orderRepository.save(order);
    }

    public Iterable<Order>  findAll(){
        return this.orderRepository.findAll();
    }

    public Iterable<Order> findByUserId(Integer userId){
        return this.orderRepository.findByUserId(userId);
    }

    public void updateStateById(Integer id , String orderStatus ){
        this.orderRepository.updateStateById(id,orderStatus);
    }

    public Order findById(Integer id){
        return  this.orderRepository.findById(id);
    }
}
