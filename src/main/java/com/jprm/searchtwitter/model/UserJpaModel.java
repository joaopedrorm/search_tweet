package com.jprm.searchtwitter.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "user")
public class UserJpaModel {

	@Id
	private String id;
	
	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<TweetJpaModel> tweetList;
	
	@Column(name = "followers_count")
	private Long followersCount;

	private String lang;
	
	private String location;
	
	private String name;
	
	@Column(name = "screen_name")
	private String screenName;
	
	@Column(length = 500)
	private String description;

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
		UserJpaModel other = (UserJpaModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"tweetList\":\"" + tweetList + "\", \"followersCount\":\"" + followersCount
				+ "\", \"lang\":\"" + lang + "\", \"location\":\"" + location + "\", \"name\":\"" + name
				+ "\", \"screenName\":\"" + screenName + "\", \"description\":\"" + description + "\"}";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(Long followersCount) {
		this.followersCount = followersCount;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
