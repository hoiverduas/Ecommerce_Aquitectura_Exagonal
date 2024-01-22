package com.maquinaria.ecommerce.backend.infrastructure.adapter;

import com.maquinaria.ecommerce.backend.domain.model.User;
import com.maquinaria.ecommerce.backend.domain.port.IUserRepository;
import com.maquinaria.ecommerce.backend.infrastructure.mapper.IUserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserCrudRepositoryImpl implements IUserRepository {

    private final IUserCrudRepository iUserCrudRepository;
    private final IUserMapper userMapper;

    public UserCrudRepositoryImpl(IUserCrudRepository iUserCrudRepository, IUserMapper userMapper) {
        this.iUserCrudRepository = iUserCrudRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        return userMapper.toUser(iUserCrudRepository.save( userMapper.toUserEntity(user) ));
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.toUser(iUserCrudRepository.findByEmail(email).orElseThrow( ()->new RuntimeException("user whit email :" + email + " not found ")));
    }

    @Override
    public User findById(Integer id) {
        return  userMapper.toUser(iUserCrudRepository.findById(id).get());
    }
}
