package com.user_service.payload;

import org.springframework.http.HttpStatus;

 

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse {

	private String message;
	private boolean succes;
	private HttpStatus status;
	
	 
}
