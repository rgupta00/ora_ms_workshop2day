package com.productapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productapp.entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    public UserEntity findByUsername(String userName);
}