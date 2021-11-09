package com.CS495.RankChoiceVoting.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CS495.RankChoiceVoting.Model.Ballot;

public interface BallotRepository extends MongoRepository<Ballot, String> {
	
	public Ballot findByBallotId(String ballotId); //mongo generated code.. prob wouold just genereated random code on our side
	Optional<Ballot> findByBallotCode(String ballotCode); //random generated code from our end
	
}