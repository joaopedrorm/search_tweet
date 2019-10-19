package com.jprm.searchtwitter.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.jprm.searchtwitter.model.UserJpaModel;

public interface  UserRepository  extends CrudRepository<UserJpaModel, String>{
	
	List<UserJpaModel> findAll(Pageable pageable);

}
