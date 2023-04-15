package com.piseth.java.school.schoolManagement.service;

import java.util.Set;

import com.piseth.java.school.schoolManagement.model.UserLogin;
import com.piseth.java.school.schoolManagement.model.UserRole;

public interface UserLoginService {
	 UserLogin getUserLoginById(Integer id);
	 
	 Set<UserLogin> listUserLogin();
	
	 UserLogin register(UserLogin userLogin);
	 
	 UserLogin updateUserRole(Integer id, Set<UserRole> userRoles);

}
