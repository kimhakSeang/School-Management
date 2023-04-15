package com.piseth.java.school.schoolManagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.piseth.java.school.schoolManagement.config.security.AuthUser;
import com.piseth.java.school.schoolManagement.config.security.PasswordEncrypt;
import com.piseth.java.school.schoolManagement.dto.UserLoginDto;
import com.piseth.java.school.schoolManagement.model.UserLogin;

@Mapper
public interface UserLoginMapper {
	
	UserLoginMapper INSTANCE = Mappers.getMapper(UserLoginMapper.class);
	
	@Mapping(target= "authorities", ignore = true)
	AuthUser toAuthUser(UserLogin userLogin);
	
	@Mapping(target= "id", ignore = true)
	@Mapping(target = "password",source = "password", qualifiedByName = "getPassword")
	@Mapping(target= "accountNonExpired", constant = "true")
	@Mapping(target= "accountNonLocked", constant = "true")
	@Mapping(target= "credentialsNonExpired", constant = "true")
	@Mapping(target= "enabled", constant = "true")
	UserLogin toUserLogin(UserLoginDto userLoginDto);
	
	@Named("getPassword")
	default String getPassword(String nPassword) {
		return new PasswordEncrypt().passwordEncoder().encode(nPassword);
	}
	
}