package com.employee.todo.entity;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Embeddable;

@Embeddable
public class Authority implements GrantedAuthority {

	private String authority;

	public Authority(String authority) {
		super();
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

}
