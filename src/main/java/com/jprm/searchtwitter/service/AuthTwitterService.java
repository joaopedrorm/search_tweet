package com.jprm.searchtwitter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.jprm.searchtwitter.entity.TwitterTokenHttpEntity;

@Service
public class AuthTwitterService {

	private static final Logger logger = LoggerFactory.getLogger(AuthTwitterService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("{config.twitter.auth.url}")
	private String twitterAuthUrl;
	
	@Value("{config.twitter.auth.username}")
	private String twitterAuthUsername;
	
	@Value("{config.twitter.auth.password}")
	private String twitterAuthPassword;

	private String cachedToken;
	
	public String getAuthToken() {

		if (cachedToken != null || !cachedToken.isBlank()) {
			return cachedToken;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(twitterAuthUsername, twitterAuthPassword);

		HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
		
		try {

			ResponseEntity<TwitterTokenHttpEntity> response = restTemplate.exchange(
					twitterAuthUrl,
					HttpMethod.POST,
					httpEntity,
					TwitterTokenHttpEntity.class);
			
			cachedToken = response.getBody().getAccessToken();
			
			return cachedToken;
		
		} catch (HttpClientErrorException e) {
			
			String errorMsg = "Erro de autenticação no twitter, HttpStatus: " + e.getStatusText();
			
			logger.error(errorMsg, e);
			
			throw new RuntimeException(errorMsg);
			
		}
	}
	
	public void invalidateCachedToken() {
		cachedToken = null;
	}
}
