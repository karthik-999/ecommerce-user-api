package com.user.app.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.app.dto.UserDto;
import com.user.app.entities.UserEntity;
import com.user.app.repositories.UsersRepository;
import com.user.app.service.interfaces.UsersService;


@Service
public class UsersServiceImpl implements UsersService {
	
	UsersRepository usersRepository;
	
	BCryptPasswordEncoder bCryptPasswordEncoder;
	//RestTemplate restTemplate;
	Environment environment;
//	AlbumsServiceClient albumsServiceClient;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public UsersServiceImpl(UsersRepository usersRepository, 
			BCryptPasswordEncoder bCryptPasswordEncoder,
//			AlbumsServiceClient albumsServiceClient,
			Environment environment)
	{
		this.usersRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//		this.albumsServiceClient = albumsServiceClient;
		this.environment = environment;
	}
 
	@Override
	public UserDto createUser(UserDto userDetails) {
		// TODO Auto-generated method stub
		
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		
		ModelMapper modelMapper = new ModelMapper(); 
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

		usersRepository.save(userEntity);
		
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
 
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = usersRepository.findByEmail(username);
		
		if(userEntity == null) throw new UsernameNotFoundException(username);	
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) { 
		UserEntity userEntity = usersRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		
		
        UserEntity userEntity = usersRepository.findByUserId(userId);
        if(userEntity == null)userEntity = usersRepository.findByEmail(userId);
        if(userEntity == null)userEntity = usersRepository.findByUserName(userId);
        if(userEntity == null) return new UserDto();
        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
		return userDto;
	}
	
	public UserEntity getUserById(Long userId) {
		
        Optional<UserEntity> userEntity = usersRepository.findById(userId);     
        if(userEntity.isEmpty()) throw new UsernameNotFoundException("User not found");
        
		return userEntity.get();
	}
	
	

}
