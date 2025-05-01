package com.employee.todo.service;

import com.employee.todo.request.AuthenticationRequest;
import com.employee.todo.request.RegisterRequest;
import com.employee.todo.response.AuthenticationResponse;

public interface AuthenticationService {

	void register(RegisterRequest registerRequest) throws Exception;

	AuthenticationResponse login(AuthenticationRequest authenticationRequest);

}
