package com.employee.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.todo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	@Query("select count(u) from User u join u.authorities a where a.authority='ROLE_ADMIN' ")
	long countAdminUsers();
}
