package com.employee.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

		return new UserResponse(user.getId(), user.getFirstName() + " " + user.getLastName(), user.getEmail(),
				user.getAuthorities().stream().map(auth -> (Authority) auth).toList());
	}

	@Override
	@Transactional
	public void deleteUser() {
		User user = findAuthenticatedUser.getAuthenticatedUser();

		if (isLastAdmin(user)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "As your are an Admin, you cannot delete yourself");
		}

		userRepository.delete(user);
	}

	private boolean isLastAdmin(User user) {
		boolean isAdmin = user.getAuthorities().stream().anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()));

		if (isAdmin) {
			long adminCount = userRepository.countAdminUsers();
			return adminCount <= 1;
		}

		return false;
	}

}
