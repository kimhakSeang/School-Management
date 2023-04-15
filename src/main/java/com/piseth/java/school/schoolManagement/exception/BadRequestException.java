package com.piseth.java.school.schoolManagement.exception;

import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class BadRequestException extends ApiException{
	private static final long serialVersionUID = 1L;
	private String filed;
	public BadRequestException(String Message) {
		super(HttpStatus.BAD_REQUEST,Message);
	}
}
