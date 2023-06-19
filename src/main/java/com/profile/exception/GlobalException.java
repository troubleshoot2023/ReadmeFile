package com.profile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(UserExist.class)
	public ResponseEntity<Object> userExist(UserExist u,WebRequest request){
		return new ResponseEntity<>(u.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<Object> userNotExist(UserNotFound u,WebRequest request){
		return new ResponseEntity<>(u.getMessage(),HttpStatus.CONFLICT);
	}
}
