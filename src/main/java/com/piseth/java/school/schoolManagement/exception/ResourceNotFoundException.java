package com.piseth.java.school.schoolManagement.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class ResourceNotFoundException extends ApiException{
	
	private String resourceName;
	private Long resourceId;
	
	public ResourceNotFoundException(String resourceName) {
		super(HttpStatus.NOT_FOUND, String.format("%s not exist!", resourceName));
	}
	

}
