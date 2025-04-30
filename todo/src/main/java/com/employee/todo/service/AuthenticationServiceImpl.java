package com.employee.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.todo.entity.Authority;
import com.employee.todo.entity.User;
import com.employee.todo.repository.UserRepository;
import com.employee.todo.request.RegisterRequest;

import jakarta.transaction.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void register(RegisterRequest input) throws Exception {

		// Check Email already used
		if (userRepository.findByEmail(input.getEmail()).isPresent()) {
			throw new Exception("Email already Taken");
		}

		// Create a new user
		User user = new User();
		user.setId(0);
		user.setFirstName(input.getFirstName());
		user.setLastName(input.getLastName());
		user.setEmail(input.getEmail());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		user.setAuthorities(getAuthorities());

		userRepository.save(user);

	}

	private List<Authority> getAuthorities() {
		List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(new Authority("ROLE_EMPLOYEE"));
		if (userRepository.count() == 0) {
			authorities.add(new Authority("ROLE_ADMIN"));
		}
		return authorities;
	}

}
