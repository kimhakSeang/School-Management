package com.piseth.java.school.schoolManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPermission {
	@Id
	private int id;
	
	@NonNull
	private String tableName;
	
	@NonNull
	private String permission;
}
