package com.arthur.ecommerce.controllers;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.arthur.ecommerce.entities.User;
import com.arthur.ecommerce.entities.dtos.AuthenticationRequest;
import com.arthur.ecommerce.entities.dtos.AuthenticationResponse;
import com.arthur.ecommerce.repositories.UserRepository;
import com.arthur.ecommerce.services.UserService;
import com.arthur.ecommerce.utils.JwtUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

		@Autowired
		private AuthenticationManager authenticationManager; // instanciado pelo proprio springSecurity
	
		@Autowired
		private UserService userService;
	
		@Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private JwtUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException,JSONException, ServletException {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
			
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Usuario ou senha incorretos");
		}	catch(DisabledException disabledException) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "usuario nao ativado");
			return null;
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		User user = userRepository.findFirstByEmail(authenticationRequest.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthenticationResponse(jwt);
		
	}
}
