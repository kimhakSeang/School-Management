package com.piseth.java.school.schoolManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piseth.java.school.schoolManagement.model.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Integer>{
	Optional<UserLogin> findByUsername(String userName);
}
