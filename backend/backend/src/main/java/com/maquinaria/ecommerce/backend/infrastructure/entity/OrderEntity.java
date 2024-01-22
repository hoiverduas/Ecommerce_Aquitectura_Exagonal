package com.maquinaria.ecommerce.backend.infrastructure.entity;


import com.maquinaria.ecommerce.backend.domain.model.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @ManyToOne
    private UserEntity userEntity;
    @OneToMany(mappedBy = "orderEntity",cascade = CascadeType.PERSIST )
    private List<OrderProductEntity> orderProducts;
}
