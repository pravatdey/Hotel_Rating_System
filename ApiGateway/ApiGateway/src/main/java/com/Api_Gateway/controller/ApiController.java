package com.Api_Gateway.controller;

 

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Api_Gateway.models.AuthResponse;
 

@RestController
@RequestMapping("/auth")
public class ApiController {
	
	private Logger logger= LoggerFactory.getLogger(ApiController.class);
	
	public ResponseEntity<AuthResponse>login(
			@RegisteredOAuth2AuthorizedClient("okat") OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OidcUser user,
			Model model
			){
		
		logger.info("user email id: {} ",user.getEmail());
		
		AuthResponse authResponse = new AuthResponse();
		
		authResponse.setUserId(user.getEmail());
		
		authResponse.setAccessToken(client.getAccessToken().getTokenValue());
		
		authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
		
		authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
		
		List<String> authorities= user.getAuthorities().stream().map(t -> {
			return t.getAuthority();
		}).collect(Collectors.toList());
		 
		authResponse.setAuthories(authorities);
		
		return new ResponseEntity<>(authResponse,HttpStatus.OK);
	}

}
