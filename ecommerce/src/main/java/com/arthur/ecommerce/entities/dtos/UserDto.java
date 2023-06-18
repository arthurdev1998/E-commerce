package com.arthur.ecommerce.entities.dtos;

import com.arthur.ecommerce.entities.enums.UserRole;

import lombok.Data;
@Data
public class UserDto {
	private Long id;
	private String name;
	private String email;
	private String password;
	private UserRole userRole;
	
	public UserDto(Long id, String email, String name, UserRole userRole) {
		
	}
	
}
