package com.user_service.config.interception;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
@Component
public class FeignClientInterceptor implements RequestInterceptor{

	private OAuth2AuthorizedClientManager clientManager;
	@Override
	public void apply(RequestTemplate template) {
		
	String token =	clientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenValue();
	 
		template.header("Authorization", "Bearer" +token);
	}
	
	

}
