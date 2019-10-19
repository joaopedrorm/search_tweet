package com.jprm.searchtwitter.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jprm.searchtwitter.entity.AggregateTweetResultEntity;
import com.jprm.searchtwitter.model.TweetJpaModel;
import com.jprm.searchtwitter.model.UserJpaModel;

@Service
public class AggregateTweetLocalService {
	
	@Autowired
	private LocalTweetService localTweetService;
	
	public AggregateTweetResultEntity getTop5UsersOrderedByFollowersCount() {
		
		List<UserJpaModel> result = localTweetService.getUsersOrderedByFollowersCount(5);
		
		AggregateTweetResultEntity resultEntity = new AggregateTweetResultEntity();
		resultEntity.setCount(Long.valueOf(result.size()));
		resultEntity.setData(result);

		return resultEntity;
	}

	
	public AggregateTweetResultEntity getTweetsCountByHourDaily() {

		List<TweetJpaModel> tweetJpaModelList = localTweetService.getAllTweets();

		Map<String, List<LocalDateTime>> groupByDayOfyear = tweetJpaModelList.stream()
				.map(TweetJpaModel::getCreateAt)
				.collect(Collectors.groupingBy(d -> d.toLocalDate().toString()));

		Map<String, Map<String, Long>> result = new HashMap<>();

		for (Entry<String, List<LocalDateTime>> entry : groupByDayOfyear.entrySet()) {

			Map<String, Long> groupByHour = entry.getValue()
					.stream()
					.collect(Collectors.groupingBy(d -> String.valueOf(d.getHour()), Collectors.counting()));

			result.putIfAbsent(entry.getKey(), groupByHour);
		}
		
		AggregateTweetResultEntity resultEntity = new AggregateTweetResultEntity();
		resultEntity.setCount(Long.valueOf(tweetJpaModelList.size()));
		resultEntity.setData(result);

		return resultEntity;
	}
	
	public AggregateTweetResultEntity getTweetsCountByTagAndLanguageLocation() {
		
		List<TweetJpaModel> tweetJpaModelList = localTweetService.getAllTweets();
		
		Map<String, Map<String, Long>> groupByTagAndLaguageLocation = tweetJpaModelList.stream()
			.collect(Collectors.groupingBy(t -> t.getTag(),
						Collectors.groupingBy(this::laguageCountryGrouper,
							Collectors.counting())));
		
		AggregateTweetResultEntity resultEntity = new AggregateTweetResultEntity();
		resultEntity.setCount(Long.valueOf(tweetJpaModelList.size()));
		resultEntity.setData(groupByTagAndLaguageLocation);

		return resultEntity;
	}
	
	private String laguageCountryGrouper(TweetJpaModel input) {
		
		String language = "Language: ";
		
		if (input.getLang() != null && !input.getLang().isBlank()) {
		
			language += input.getLang();
		
		} else if (input.getUser().getLang() != null && !input.getUser().getLang().isBlank()) {
			
			language += input.getUser().getLang();
			
		} else {
			
			language += "Unavailable";
			
		}
		
		String place = "Location: ";
		
		if (input.getPlace() != null && !input.getPlace().isBlank()) {
			
			place += input.getPlace();
		
		} else if (input.getUser().getLocation() != null && !input.getUser().getLocation().isBlank()) {
			
			place += input.getUser().getLocation();
			
		} else {
			
			place += "Unavailable";
			
		}
		
		return language + " - " + place;
	}
}
