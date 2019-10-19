package com.jprm.searchtwitter.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tweet")
public class TweetJpaModel {
	
	@Id
	private String id;
	
	@Column(name = "created_at")
	private LocalDateTime createAt;
	
	private String tag;

	@ManyToOne
	@JsonManagedReference
	private UserJpaModel user;

	private String place;
	
	private String lang;

	@Column(length = 500)
	private String text;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TweetJpaModel other = (TweetJpaModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"createAt\":\"" + createAt + "\", \"tag\":\"" + tag + "\", \"user\":\"" + user
				+ "\", \"place\":\"" + place + "\", \"lang\":\"" + lang + "\", \"text\":\"" + text + "\"}";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public UserJpaModel getUser() {
		return user;
	}

	public void setUser(UserJpaModel user) {
		this.user = user;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
