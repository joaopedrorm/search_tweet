package com.jprm.searchtwitter.repository;

import org.springframework.data.repository.CrudRepository;

import com.jprm.searchtwitter.model.UserJpaModel;

public interface  UserRepository  extends CrudRepository<UserJpaModel, String>{

}
