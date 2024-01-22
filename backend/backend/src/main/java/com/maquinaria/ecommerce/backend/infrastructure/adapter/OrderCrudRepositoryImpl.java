package com.maquinaria.ecommerce.backend.infrastructure.adapter;

import com.maquinaria.ecommerce.backend.domain.model.Order;
import com.maquinaria.ecommerce.backend.domain.model.OrderStatus;
import com.maquinaria.ecommerce.backend.domain.port.IOrderRepository;
import com.maquinaria.ecommerce.backend.infrastructure.entity.OrderEntity;
import com.maquinaria.ecommerce.backend.infrastructure.entity.UserEntity;
import com.maquinaria.ecommerce.backend.infrastructure.mapper.IOrderMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCrudRepositoryImpl implements IOrderRepository {

    private final IOrderCrudRepository orderCrudRepository;
    private  final IOrderMapper orderMapper;

    public OrderCrudRepositoryImpl(IOrderCrudRepository orderCrudRepository, IOrderMapper orderMapper) {
        this.orderCrudRepository = orderCrudRepository;
        this.orderMapper = orderMapper;
    }



    @Override
    public Order save(Order order) {

        OrderEntity orderEntity = orderMapper.toOrderEntity(order);

        orderEntity.getOrderProducts().forEach(
                orderProductEntity -> orderProductEntity.setOrderEntity(orderEntity)
        );
        return orderMapper.toOrder(orderCrudRepository.save(orderEntity));
    }

    @Override
    public Iterable<Order> findAll() {
        return orderMapper.toOderList(orderCrudRepository.findAll());
    }

    @Override
    public Iterable<Order> findByUserId(Integer userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return orderMapper.toOderList(orderCrudRepository.findByUserEntity(userEntity));
    }

    @Override
    public Order findById(Integer id) {
        return orderMapper.toOrder(orderCrudRepository.findById(id).orElseThrow(()-> new RuntimeException("Orden con id "+id+ " no existe ")));
    }

    @Override
    public void updateStateById(Integer id, String status) {
          if(status.equals(OrderStatus.CANCELLED)){
              orderCrudRepository.updateStateById(id,OrderStatus.CANCELLED);
          }else {
              orderCrudRepository.updateStateById(id,OrderStatus.CONFIRMED);
          }
    }
}
