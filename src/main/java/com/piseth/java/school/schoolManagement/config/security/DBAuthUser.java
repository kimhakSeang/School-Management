package com.piseth.java.school.schoolManagement.config.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.piseth.java.school.schoolManagement.mapper.UserLoginMapper;
import com.piseth.java.school.schoolManagement.model.UserLogin;
import com.piseth.java.school.schoolManagement.repository.UserLoginRepository;


@Service
public class DBAuthUser implements UserDetailServices {
	@Autowired
	private UserLoginRepository userLoginRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		 UserLogin userLogin = userLoginRepository
				 						.findByUsername(username)
				 						.orElseThrow(()-> new UsernameNotFoundException("%s not exist.".formatted(username)));

		 AuthUser authUser = UserLoginMapper.INSTANCE.toAuthUser(userLogin);
		 
		 //filter Authority
		 Set<GrantedAuthority> authority = userLogin.getUserRoles()
							.stream()
		 					.flatMap(ur -> ur.getPermissions().stream())
		 					.map(s->new SimpleGrantedAuthority(s.getPermission()))
		 					.collect(Collectors.toSet());
		 authUser.setAuthorities(authority);
		 
		 return authUser;
	}
	
	
}
