package com.employee.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.todo.entity.Authority;
import com.employee.todo.entity.User;
import com.employee.todo.repository.UserRepository;
import com.employee.todo.response.UserResponse;
import com.employee.todo.util.FindAuthenticatedUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FindAuthenticatedUser findAuthenticatedUser;

	@Override
	@Transactional(readOnly = true)
	public UserResponse getUserInfor() {

		User user = findAuthenticatedUser.getAuthenticatedUser();
		
		System.out.println(user);

		return new UserResponse(user.getId(), user.getFirstName() + " " + user.getLastName(), user.getEmail(),
				user.getAuthorities().stream().map(auth -> (Authority) auth).toList());
	}

}
