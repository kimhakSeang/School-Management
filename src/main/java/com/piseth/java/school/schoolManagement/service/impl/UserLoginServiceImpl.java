package com.piseth.java.school.schoolManagement.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piseth.java.school.schoolManagement.dto.UserLoginDto;
import com.piseth.java.school.schoolManagement.exception.BadRequestException;
import com.piseth.java.school.schoolManagement.exception.ResourceNotFoundException;
import com.piseth.java.school.schoolManagement.model.UserLogin;
import com.piseth.java.school.schoolManagement.model.UserRole;
import com.piseth.java.school.schoolManagement.repository.UserLoginRepository;
import com.piseth.java.school.schoolManagement.service.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService{
	@Autowired
	private UserLoginRepository userloginRepository;
//	@Autowired
//	private UserRoleRepository userRoleRepository;
	
	@Override
	public UserLogin getUserLoginById(Integer id) {
		return userloginRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User"));
	}

	@Override
	public Set<UserLogin> listUserLogin() {
		return userloginRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public UserLogin register(UserLogin userLogin) {
		//Optional<UserLogin> userLogin = userloginRepository.findById(userLoginDto.getId());
//		if(userLogin.isPresent()) {
//			throw new BadRequestException("User ready exist!");
//		}else {
		System.out.println(">>>>>>>>>>>>>>>"+userLogin);
			return userloginRepository.save(userLogin);
		//}
	}
	
	@Override
	public UserLogin updateUserRole(Integer id, Set<UserRole> userRoles) {
		 System.out.println(">>>>>>>>>>userRoles: "+userRoles);
		 UserLogin userLogin = getUserLoginById(id);
		 userLogin.setUserRoles(userRoles);
		 System.out.println(">>>>>>>>>>userLogin : "+userLogin);
		 userloginRepository.save(userLogin);
		 return userLogin;
	}


}
