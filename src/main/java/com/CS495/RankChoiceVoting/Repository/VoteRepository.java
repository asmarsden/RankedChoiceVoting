package com.CS495.RankChoiceVoting.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CS495.RankChoiceVoting.Model.Vote;

public interface VoteRepository extends MongoRepository<Vote, String> {
	
	
}
