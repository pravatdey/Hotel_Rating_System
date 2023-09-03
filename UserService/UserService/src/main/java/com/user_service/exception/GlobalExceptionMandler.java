package com.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.user_service.payload.APIResponse;

  

public class GlobalExceptionMandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse>handlerResourceNotFoundException(ResourceNotFoundException ex){
		String message=ex.getMessage();
		APIResponse response =APIResponse.builder().message(message).succes(true).status(HttpStatus.NOT_FOUND).build();
	    return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	    
	}
}
