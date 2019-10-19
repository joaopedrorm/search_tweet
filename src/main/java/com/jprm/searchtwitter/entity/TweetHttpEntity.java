package com.jprm.searchtwitter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetHttpEntity {
	
	@JsonProperty("created_at")
	private String createAt;
	
	@JsonProperty("id_str")
	private String idStr;
	
	private String text;
	
	private UserHttpEntity user;
	
	private TweetPlaceHttpEntity place;
	
	private String lang;

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserHttpEntity getUser() {
		return user;
	}

	public void setUser(UserHttpEntity user) {
		this.user = user;
	}

	public TweetPlaceHttpEntity getPlace() {
		return place;
	}

	public void setPlace(TweetPlaceHttpEntity place) {
		this.place = place;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
