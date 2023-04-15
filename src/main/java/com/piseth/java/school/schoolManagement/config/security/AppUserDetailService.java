package com.piseth.java.school.schoolManagement.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService  {
	
	@Autowired
	private UserDetailServices detailServices;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return detailServices.loadUserByUsername(username);
	}

}
