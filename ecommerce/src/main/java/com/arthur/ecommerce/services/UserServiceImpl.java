package com.arthur.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arthur.ecommerce.entities.User;
import com.arthur.ecommerce.entities.dtos.SignupDto;
import com.arthur.ecommerce.entities.dtos.UserDto;
import com.arthur.ecommerce.entities.enums.UserRole;
import com.arthur.ecommerce.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto createUser(SignupDto signupDto) {
		
		User user = new User();
		user.setName(signupDto.getName());
		user.setEmail(signupDto.getEmail());
		user.setUserRole(UserRole.USER);
		user.setPassword(new BCryptPasswordEncoder().encode(signupDto.getPassword()));
		User createdUser = userRepository.save(user);
		
		
		UserDto userDTO = new UserDto(); 
		userDTO.setId(createdUser.getId());
		userDTO.setName(createdUser.getName());
		userDTO.setEmail(createdUser.getEmail());
		userDTO.setPassword(createdUser.getPassword());
		userDTO.setUserRole(createdUser.getUserRole());
		
		
		return userDTO;
		
	
	}

}
