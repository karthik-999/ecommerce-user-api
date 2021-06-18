package com.user.app.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.user.app.dto.UserDto;
import com.user.app.entities.UserEntity;

public interface UsersService extends UserDetailsService{
	UserDto createUser(UserDto userDetails);
	UserDto getUserDetailsByEmail(String email);
	UserDto getUserByUserId(String userId);
	UserEntity getUserById(Long userId);
}
	