package com.employee.todo.service;

import com.employee.todo.request.RegisterRequest;

public interface AuthenticationService {

	void register(RegisterRequest registerRequest) throws Exception;

}
