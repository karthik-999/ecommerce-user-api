package com.user.app.controller;

 import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.app.dto.UserDto;
import com.user.app.entities.CreateUserRequestModel;
import com.user.app.entities.CreateUserResponseModel;
import com.user.app.entities.UserEntity;
import com.user.app.entities.UserResponseModel;
import com.user.app.service.interfaces.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	UsersService usersService;

	@GetMapping("/status/check")
	public String status()
	{
		return "Working on port " + env.getProperty("local.server.port") + ", with token = " + env.getProperty("token.secret");
	}
 
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userDetails)
	{
		ModelMapper modelMapper = new ModelMapper(); 
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		
		UserDto createdUser = usersService.createUser(userDto);
		
		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
    @GetMapping(value="/{userId}")
    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {
       
        UserDto userDto = usersService.getUserByUserId(userId); 
        UserResponseModel returnValue = new ModelMapper().map(userDto, UserResponseModel.class);
        
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
	
    
    @GetMapping(value="/user/{userId}")
    public ResponseEntity<UserEntity> getUser1(@PathVariable("userId") Long userId) {
       
        UserEntity userEntity =  usersService.getUserById(userId); 
        return ResponseEntity.status(HttpStatus.OK).body(userEntity);
    }
}
