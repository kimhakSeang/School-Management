package com.piseth.java.school.schoolManagement.config.security;

import static com.piseth.java.school.schoolManagement.config.security.PermissionEnum.MS_R;
import static com.piseth.java.school.schoolManagement.config.security.PermissionEnum.MS_W;
import static com.piseth.java.school.schoolManagement.config.security.PermissionEnum.STU_R;
import static com.piseth.java.school.schoolManagement.config.security.PermissionEnum.STU_W;
import static com.piseth.java.school.schoolManagement.config.security.PermissionEnum.SUB_R;
import static com.piseth.java.school.schoolManagement.config.security.PermissionEnum.SUB_W;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
	STUDENT(Set.of(MS_R, STU_R, SUB_R)) ,
	TEACHER(Set.of(MS_R, STU_R, SUB_R, MS_W, STU_W, SUB_W)) ;
	
	private Set<PermissionEnum> permissions;
	private UserRoleEnum(Set<PermissionEnum> permissions) {
		this.permissions = permissions;
	}
	public Set<SimpleGrantedAuthority> getAuthority(){
		return this.permissions.stream().map(p->new SimpleGrantedAuthority(p.getDescript())).collect(Collectors.toSet());
	}
	
}
