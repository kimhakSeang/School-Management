package com.piseth.java.school.schoolManagement.dto;

import java.util.Set;

import com.piseth.java.school.schoolManagement.model.UserPermission;
import com.piseth.java.school.schoolManagement.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
	
		private Integer id;
		private String username;
		private String password;
		
		private Set<UserRole> userRoles = Set.of(new UserRole(1, "STUDENT", Set.of(new UserPermission(1, "STUDENT", "READ"))));
}
