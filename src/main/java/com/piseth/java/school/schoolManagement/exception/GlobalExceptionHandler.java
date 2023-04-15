package com.piseth.java.school.schoolManagement.exception;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({ApiException.class})
	public ResponseEntity<?> handleHttpClientError(ApiException e){
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getStatus().getReasonPhrase());
		return ResponseEntity.status(e.getStatus()).body(errorResponse);
	}
	
	@ExceptionHandler(PSQLException.class)
	public ResponseEntity<?> DBHandler(PSQLException e){
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> DBHandler(HttpRequestMethodNotSupportedException e){
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleHttpClientError(IllegalArgumentException e){
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
}
