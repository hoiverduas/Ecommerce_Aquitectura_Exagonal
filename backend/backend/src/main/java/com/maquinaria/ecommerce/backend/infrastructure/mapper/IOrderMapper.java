package com.maquinaria.ecommerce.backend.infrastructure.mapper;

import com.maquinaria.ecommerce.backend.domain.model.Order;
import com.maquinaria.ecommerce.backend.infrastructure.entity.OrderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",uses = {IOrderProductMapper.class})
public interface IOrderMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "dateCreated", target = "dateCreated"),
            @Mapping(source = "orderProducts", target = "orderProducts"),
            @Mapping(source = "orderStatus", target = "orderStatus"),
            @Mapping(source = "userEntity.id", target = "userId")

    })
    Order toOrder(OrderEntity orderEntity);
    Iterable<Order> toOderList(Iterable<OrderEntity>orders);
    @InheritInverseConfiguration
    OrderEntity toOrderEntity(Order order);
}
