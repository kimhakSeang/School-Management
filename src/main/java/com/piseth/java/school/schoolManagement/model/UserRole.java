package com.piseth.java.school.schoolManagement.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_role")
public class UserRole {
	@Id
	private int id;
	
	@NonNull
	private String roleName;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<UserPermission> permissions;

}
