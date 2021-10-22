package com.CS495.RankChoiceVoting.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CS495.RankChoiceVoting.Model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findUserByUserID (String userID);
}
