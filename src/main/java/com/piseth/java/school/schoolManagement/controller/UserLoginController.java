package com.piseth.java.school.schoolManagement.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.piseth.java.school.schoolManagement.dto.UserLoginDto;
import com.piseth.java.school.schoolManagement.mapper.UserLoginMapper;
import com.piseth.java.school.schoolManagement.model.UserLogin;
import com.piseth.java.school.schoolManagement.model.UserPermission;
import com.piseth.java.school.schoolManagement.model.UserRole;
import com.piseth.java.school.schoolManagement.service.UserLoginService;

@RequestMapping("/login")
@Controller
public class UserLoginController {
	@Autowired
	private UserLoginService userLoginService;
	
	@PostMapping
	public ResponseEntity<UserLogin> createUserLogin(@RequestBody UserLoginDto userLoginDto) {
		UserLogin userLogin = UserLoginMapper.INSTANCE.toUserLogin(userLoginDto);
		return ResponseEntity.ok(userLoginService.register(userLogin));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserLogin(@PathVariable Integer id) {
		//return ResponseEntity.ok(userLoginService.getUserLoginById(id));
		
		return ResponseEntity.ok(Set.of(new UserRole(1, "STUDENT", Set.of(new UserPermission(1, "STUDENT", "READ")))));
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getListUserLogin() {
		return ResponseEntity.ok(userLoginService.listUserLogin());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserRole(@PathVariable Integer id, @RequestBody Set<UserRole> userRoles) {
		return ResponseEntity.ok(userLoginService.updateUserRole(id, userRoles));
	}

}
