package com.employee.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employee.todo.request.AuthenticationRequest;
import com.employee.todo.request.RegisterRequest;
import com.employee.todo.response.AuthenticationResponse;
import com.employee.todo.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication REST API Endpoints", description = "Operations related to register & login")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@Operation(summary = "Register a user", description = "Create a new user in database")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/register")
	public void register(@Valid @RequestBody RegisterRequest registerRequest) throws Exception {
		authenticationService.register(registerRequest);
	}
	
	
	@Operation(summary = "Login a user", description = "submit email & password to authenticate user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
	public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
		return authenticationService.login(authenticationRequest);
	}

}
