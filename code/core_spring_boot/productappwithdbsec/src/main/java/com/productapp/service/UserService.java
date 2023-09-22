package com.productapp.service;

import com.productapp.entities.UserEntity;

public interface UserService {
    public UserEntity findByUsername(String username);
    public void addUserEntity(UserEntity userEntity);
}
