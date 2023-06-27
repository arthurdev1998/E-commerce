package com.arthur.ecommerce.entities.dtos;

import com.arthur.ecommerce.entities.enums.UserRole;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data

public class UserDto {
	private Long id;
	private String name;
	private String email;
	private String password;
	private UserRole userRole;
	
	
	
}
