package com.piseth.java.school.schoolManagement.config.security;

import static com.piseth.java.school.schoolManagement.config.security.Permission.MS_R;
import static com.piseth.java.school.schoolManagement.config.security.Permission.MS_W;
import static com.piseth.java.school.schoolManagement.config.security.Permission.STU_R;
import static com.piseth.java.school.schoolManagement.config.security.Permission.STU_W;
import static com.piseth.java.school.schoolManagement.config.security.Permission.SUB_R;
import static com.piseth.java.school.schoolManagement.config.security.Permission.SUB_W;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;

@Getter
public enum Role {
	STUDENT(Set.of(MS_R, STU_R, SUB_R)) ,
	TEACHER(Set.of(MS_R, STU_R, SUB_R, MS_W, STU_W, SUB_W)) ;
	
	private Set<Permission> permissions;
	private Role(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	public Set<SimpleGrantedAuthority> getAuthority(){
		return this.permissions.stream().map(p->new SimpleGrantedAuthority(p.getDescript())).collect(Collectors.toSet());
	}
	  
	
	
}
