package com.maquinaria.ecommerce.backend.application.service;

import com.maquinaria.ecommerce.backend.domain.model.User;
import com.maquinaria.ecommerce.backend.domain.port.IUserRepository;

public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public User  findById(Integer id){
        return this.userRepository.findById(id);
    }
}
