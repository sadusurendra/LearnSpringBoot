package com.employee.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employee.todo.response.UserResponse;
import com.employee.todo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User REST API Endpoints", description = "Operations related to info about current user")
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "User information", description = "Get current user info")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/info")
	public UserResponse getUserInfo() {
		return userService.getUserInfor();
	}

	@Operation(summary = "Delete user", description = "Delete current user account")
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping
	public void deleteUser() {
		userService.deleteUser();
	}

}
