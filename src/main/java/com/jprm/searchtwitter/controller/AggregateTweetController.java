package com.jprm.searchtwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jprm.searchtwitter.entity.AggregateTweetResultEntity;
import com.jprm.searchtwitter.entity.AgrregateTweetHttpResponseWrapperEntity;
import com.jprm.searchtwitter.service.AggregateTweetLocalService;

@RestController
public class AggregateTweetController {
	
	@Autowired
	private AggregateTweetLocalService aggregateLocalTweetService;

	@GetMapping("/local/user/top5byfollowerscount")
	public AgrregateTweetHttpResponseWrapperEntity getTop5UsersByFollowersCount() {
		
		return fromAggregateTweetResultEntityToAggregateTweetResultEntity(
				aggregateLocalTweetService.getTop5UsersOrderedByFollowersCount(),
				"top5byfollowerscount");
	}
	
	@GetMapping("/local/tweet/countbyhourdaily")
	public AgrregateTweetHttpResponseWrapperEntity getTweetsCountByHourDaily() {
		
		return fromAggregateTweetResultEntityToAggregateTweetResultEntity(
				aggregateLocalTweetService.getTweetsCountByHourDaily(),
				"countbyhourdaily");
	}
	
	@GetMapping("/local/tweet/countbytagandlanguagelocation")
	public AgrregateTweetHttpResponseWrapperEntity getTweetsCountByTagAndLanguageLocation() {
		
		return fromAggregateTweetResultEntityToAggregateTweetResultEntity(
				aggregateLocalTweetService.getTweetsCountByTagAndLanguageLocation(),
				"countbytagandlanguagelocation");
	}
	
	private AgrregateTweetHttpResponseWrapperEntity fromAggregateTweetResultEntityToAggregateTweetResultEntity(AggregateTweetResultEntity result, String report) {
		
		AgrregateTweetHttpResponseWrapperEntity response = new AgrregateTweetHttpResponseWrapperEntity();
		
		response.setCount(result.getCount());
		response.setData(result.getData());
		response.setReport(report);
		
		return response;
	}
}
