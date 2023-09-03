package com.hotel.exception;

public class ResourceNotFoundException extends RuntimeException{
  
	public ResourceNotFoundException() {
		super("Resource not found on server");
	}
	
	public ResourceNotFoundException(String s) {
		super(s);
	}
	
	
}
