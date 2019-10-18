package com.jprm.searchtwitter.repository;

import org.springframework.data.repository.CrudRepository;

import com.jprm.searchtwitter.model.TweetJpaModel;

public interface TweetRepository extends CrudRepository<TweetJpaModel, String>{

}
