package com.jprm.searchtwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.jprm.searchtwitter.entity.TwitterTokenHttpEntity;

@Service
public class AuthTwitterService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${config.twitter.auth.url}")
	private String twitterAuthUrl;
	
	@Value("${config.twitter.auth.username}")
	private String twitterAuthUsername;
	
	@Value("${config.twitter.auth.password}")
	private String twitterAuthPassword;

	private String cachedToken;
	
	public String getAuthToken() {

		if (cachedToken != null && !cachedToken.isBlank()) {
			return cachedToken;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(twitterAuthUsername, twitterAuthPassword);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.set("grant_type", "client_credentials");
		
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers);
		
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
			
			throw new RuntimeException(errorMsg, e);
			
		}
	}
	
	public void invalidateCachedToken() {
		cachedToken = null;
	}
}
