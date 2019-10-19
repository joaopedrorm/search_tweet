package com.jprm.searchtwitter.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.httpclient.LogbookHttpRequestInterceptor;
import org.zalando.logbook.httpclient.LogbookHttpResponseInterceptor;

@Configuration
public class RestTemplateConfig {

	@Autowired
	private Logbook logbook;
	
	@Bean
	public RestTemplate restTemplate() {

		CloseableHttpClient client = HttpClientBuilder.create()
		        .addInterceptorFirst(new LogbookHttpRequestInterceptor(logbook))
		        .addInterceptorFirst(new LogbookHttpResponseInterceptor())
		        .disableContentCompression()
		        .build();
		
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
		
		//restTemplate.
		
		return restTemplate;

	}
	
}
