package com.maquinaria.ecommerce.backend.infrastructure.config;

import com.maquinaria.ecommerce.backend.application.service.*;

import com.maquinaria.ecommerce.backend.domain.port.ICategoryRepository;
import com.maquinaria.ecommerce.backend.domain.port.IOrderRepository;
import com.maquinaria.ecommerce.backend.domain.port.IProductRepository;
import com.maquinaria.ecommerce.backend.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {

    @Bean
    public UserService userService(IUserRepository userRepository){
        return new UserService(userRepository);
    }

    @Bean
    public CategoryService categoryService(ICategoryRepository categoryRepository){return new CategoryService(categoryRepository);}

    @Bean
    public ProductService productService(IProductRepository productRepository, UploadFile uploadFile){
        return new ProductService(productRepository, uploadFile);
    }
    @Bean
    public OrderService orderService(IOrderRepository orderRepository){
        return new OrderService(orderRepository);
    }

    @Bean
    public UploadFile uploadFile(){
        return new UploadFile();
    }

    @Bean
    public RegistrationService registrationService(IUserRepository userRepository){
        return new RegistrationService(userRepository);
    }
}

