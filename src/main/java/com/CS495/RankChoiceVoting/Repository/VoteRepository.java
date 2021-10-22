package com.CS495.RankChoiceVoting.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CS495.RankChoiceVoting.Model.Vote;

public interface VoteRepository extends MongoRepository<Vote, String> {
	
	public Vote findByVoteID(String voteID); //mongo generated code.. prob wouold just genereated random code on our side
	Optional<Vote> findByVoteCode(String vID); //random generated code from our end
	
}
