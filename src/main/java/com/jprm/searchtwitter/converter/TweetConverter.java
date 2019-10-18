package com.jprm.searchtwitter.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jprm.searchtwitter.entity.TweetHttpEntity;
import com.jprm.searchtwitter.model.TweetJpaModel;

@Component
public class TweetConverter {

	public List<TweetJpaModel> fromTweetHttpEntityListToTweetJpaModelList(List<TweetHttpEntity> tweetHttpEntityList) {
		
		if(tweetHttpEntityList == null) {
			
			return Collections.emptyList();
		
		} else {
		
			return tweetHttpEntityList.stream()
					.map(this::fromTweetHttpEntityToTweetJpaModel)
					.collect(Collectors.toList());
		}

	}
	
	public TweetJpaModel fromTweetHttpEntityToTweetJpaModel(TweetHttpEntity tweetHttpEntity) {
		
		//return null;
		throw new RuntimeException("Not implemented");
	}
	
}
