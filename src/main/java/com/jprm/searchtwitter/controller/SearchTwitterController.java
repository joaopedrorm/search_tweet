package com.jprm.searchtwitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jprm.searchtwitter.converter.TweetConverter;
import com.jprm.searchtwitter.entity.TweetHttpEntity;
import com.jprm.searchtwitter.model.TweetJpaModel;
import com.jprm.searchtwitter.service.LocalTweetService;
import com.jprm.searchtwitter.service.SearchTwitterService;

@RestController
public class SearchTwitterController {
	
	@Autowired
	private SearchTwitterService searchTwitterService;
	
	@Autowired
	private TweetConverter tweetConverter;
	
	@Autowired
	private LocalTweetService localTweetService; 
	
	@GetMapping("/twitter/search/tag/{tag}")
	public List<TweetJpaModel> getTweetsByTag(@PathVariable("tag") String tag) {
		
		List<TweetHttpEntity> tweetHttpEntityList = searchTwitterService.searchTweetsByTag(tag);
		
		List<TweetJpaModel> tweetJpaModelList = tweetConverter.fromTweetHttpEntityListToTweetJpaModelList(tweetHttpEntityList);
		
		return localTweetService.saveAll(tweetJpaModelList);
	}

}
