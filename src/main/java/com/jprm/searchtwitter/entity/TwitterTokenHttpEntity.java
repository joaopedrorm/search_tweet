package com.jprm.searchtwitter.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitterTokenHttpEntity {
	
	@JsonProperty("token_type")
	private String tokenType;
	
	@JsonProperty("access_token")
	private String accessToken;

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
