package com.jprm.searchtwitter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jprm.searchtwitter.model.UserJpaModel;
import com.jprm.searchtwitter.service.AggregateLocalTweetService;

@RestController
public class AggregateTweetController {
	
	@Autowired
	private AggregateLocalTweetService aggregateLocalTweetService;

	@GetMapping("/local/user/top5byfollowerscount")
	public List<UserJpaModel> getTop5UsersByFollowersCount() {
		
		return aggregateLocalTweetService.getTop5UsersOrderedByFollowersCount();
	}
	
	@GetMapping("/local/tweet/countbyhourdaily")
	public Map<String, Map<String, Long>> getTweetsCountByHourDaily() {
		
		return aggregateLocalTweetService.getTweetsCountByHourDaily();
	}
}
