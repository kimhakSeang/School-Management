package com.piseth.java.school.schoolManagement.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailServices{
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
