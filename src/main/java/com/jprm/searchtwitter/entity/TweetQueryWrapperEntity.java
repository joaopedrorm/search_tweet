package com.jprm.searchtwitter.entity;

import java.util.List;

public class TweetQueryWrapperEntity {
	
	private List<TweetHttpEntity> statuses;

	public List<TweetHttpEntity> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<TweetHttpEntity> statuses) {
		this.statuses = statuses;
	}
}
