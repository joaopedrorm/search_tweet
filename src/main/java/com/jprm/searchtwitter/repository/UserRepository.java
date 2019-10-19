package com.jprm.searchtwitter.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jprm.searchtwitter.model.UserJpaModel;

public interface  UserRepository  extends CrudRepository<UserJpaModel, String>{
	
	@Query(" SELECT U FROM UserJpaModel U ")
	List<UserJpaModel> findAllUsers(Pageable pageable);

}
