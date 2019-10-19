package com.jprm.searchtwitter.converter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jprm.searchtwitter.entity.TweetHttpEntity;
import com.jprm.searchtwitter.entity.UserHttpEntity;
import com.jprm.searchtwitter.model.TweetJpaModel;
import com.jprm.searchtwitter.model.UserJpaModel;

@Component
public class TweetConverter {
	
	private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss ZZZ yyyy");

	public List<TweetJpaModel> fromTweetHttpEntityListToTweetJpaModelList(List<TweetHttpEntity> tweetHttpEntityList, String tag) {
		
		if(tweetHttpEntityList == null) {
			
			return Collections.emptyList();
		
		} else {
		
			return tweetHttpEntityList.stream()
					.map(t -> fromTweetHttpEntityToTweetJpaModel(t, tag))
					.collect(Collectors.toList());
		}

	}
	
	public TweetJpaModel fromTweetHttpEntityToTweetJpaModel(TweetHttpEntity tweetHttpEntity, String tag) {
		
		TweetJpaModel tweetJpaModel = new TweetJpaModel();
		
		tweetJpaModel.setTag(tag);
		tweetJpaModel.setCreateAt(fromStringToLocalDateTime(tweetHttpEntity.getCreateAt()));
		tweetJpaModel.setId(tweetHttpEntity.getIdStr());
		tweetJpaModel.setLang(tweetHttpEntity.getLang());
		tweetJpaModel.setPlace(tweetHttpEntity.getPlace());
		tweetJpaModel.setText(tweetHttpEntity.getText());
		tweetJpaModel.setUser(fromUserHttpEntityToUserJpaModel(tweetHttpEntity.getUser()));
		
		return tweetJpaModel;
	}
	
	public LocalDateTime fromStringToLocalDateTime(String timeStamp) {
		return ZonedDateTime.parse(timeStamp, fmt).toLocalDateTime();
	}
	
	public UserJpaModel fromUserHttpEntityToUserJpaModel(UserHttpEntity userHttpEntity) {
		
		UserJpaModel userJpaModel = new UserJpaModel();
		
		userJpaModel.setDescription(userHttpEntity.getDescription());
		userJpaModel.setFollowersCount(userHttpEntity.getFollowersCount());
		userJpaModel.setId(userHttpEntity.getIdStr());
		userJpaModel.setLang(userHttpEntity.getLang());
		userJpaModel.setLocation(userHttpEntity.getLocation());
		userJpaModel.setName(userHttpEntity.getName());
		userJpaModel.setScreenName(userHttpEntity.getScreenName());
		
		return userJpaModel;
	}
	
}
