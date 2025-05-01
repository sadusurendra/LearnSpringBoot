package com.employee.todo.util;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.employee.todo.entity.User;

@Service
public class FindAuthenticatedUserImpl implements FindAuthenticatedUser {

	@Override
	public User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		System.out.println("authentication......... "+authentication);
		
		if (authentication == null || !authentication.isAuthenticated()
				|| authentication.getPrincipal().equals("anonymousUser")) {
			throw new AccessDeniedException("Authentication Required");
		}

		return (User) authentication.getPrincipal();
	}

}
