package com.arthur.ecommerce.services;


import com.arthur.ecommerce.entities.dtos.SignupDto;
import com.arthur.ecommerce.entities.dtos.UserDto;


public interface UserService {

	UserDto createUser(SignupDto signupDto);

}
