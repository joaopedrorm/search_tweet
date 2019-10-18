package com.jprm.searchtwitter.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jprm.searchtwitter.model.TweetJpaModel;
import com.jprm.searchtwitter.model.UserJpaModel;
import com.jprm.searchtwitter.repository.TweetRepository;
import com.jprm.searchtwitter.repository.UserRepository;

@Service
public class LocalTweetService {
	
	//private static final Logger logger = LoggerFactory.getLogger(LocalTweetService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TweetRepository tweetRepository;
	
	public List<TweetJpaModel> saveAll(List<TweetJpaModel> tweetJpaModelList) {
		
		Set<UserJpaModel> userJpaModelSet = tweetJpaModelList.stream()
				.map(TweetJpaModel::getUser)
				.collect(Collectors.toSet());
		
		userRepository.saveAll(userJpaModelSet);
		
		return (List<TweetJpaModel>)tweetRepository.saveAll(tweetJpaModelList);
	}

}
