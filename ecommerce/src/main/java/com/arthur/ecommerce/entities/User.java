package com.arthur.ecommerce.entities;

import com.arthur.ecommerce.entities.dtos.UserDto;
import com.arthur.ecommerce.entities.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Table(name="users")
@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	private UserRole userRole;
	private byte[] img;
	
	public UserDto mapUserToUserDto() {
		return new UserDto(id, email,name,userRole);
	}
}
