package com.maquinaria.ecommerce.backend.application.service;

import com.maquinaria.ecommerce.backend.domain.model.User;
import com.maquinaria.ecommerce.backend.domain.port.IUserRepository;

public class RegistrationService {

    private final IUserRepository userRepository;


    public RegistrationService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user){

        return userRepository.save(user);

    }
}
