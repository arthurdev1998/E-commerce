package com.arthur.ecommerce.entities.dtos;

import lombok.Data;

@Data
public class AuthenticationRequest {

	private String userName;
	private String password;
}
