package com.arthur.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.ecommerce.entities.dtos.SignupDto;
import com.arthur.ecommerce.entities.dtos.UserDto;
import com.arthur.ecommerce.services.UserService;

@RestController
public class SignupController {

	@Autowired 
	private UserService userService;
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> singupUser(@RequestBody SignupDto signupDto ){
		UserDto createdUser = userService.createUser(signupDto);
		
		if(createdUser == null ) {
			return new ResponseEntity<>("usuario nao criado, tente mais tarde", HttpStatus.NOT_ACCEPTABLE);
			
		}
			return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
		
	}
}
