package com.user.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.user.app.entities.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	UserEntity findByUserName(String userId);
}