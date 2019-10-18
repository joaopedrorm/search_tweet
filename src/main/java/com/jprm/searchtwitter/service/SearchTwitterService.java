package com.jprm.searchtwitter.service;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jprm.searchtwitter.entity.TweetHttpEntity;
import com.jprm.searchtwitter.entity.TweetQueryWrapperEntity;

@Service
public class SearchTwitterService {
	
	private static final Logger logger = LoggerFactory.getLogger(LocalTweetService.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AuthTwitterService authTwitterService;

	@Value("{config.twitter.search.url}")
	private String twitterSearchUrl;

	public List<TweetHttpEntity> searchTweetsByTag(String tag) {

		URI uri = UriComponentsBuilder.fromHttpUrl(twitterSearchUrl + "?q={}&result_type=recent&count=100")
				.build(tag);

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(authTwitterService.getAuthToken());

		HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

		try {
		
			ResponseEntity<TweetQueryWrapperEntity> response = restTemplate.exchange(
					uri,
					HttpMethod.GET,
					httpEntity,
					TweetQueryWrapperEntity.class);
	
			return response.getBody().getStatuses();
	
		} catch (HttpClientErrorException e) {
			
			if (HttpStatus.UNAUTHORIZED.equals(e.getStatusCode())) {
				authTwitterService.invalidateCachedToken();
			}
			
			String errorMsg = "Erro ao consultar api /search do twitter, HttpStatus: " + e.getStatusText();
			
			logger.error(errorMsg, e);
			
			throw new RuntimeException(errorMsg);
		}
		
	}

}

