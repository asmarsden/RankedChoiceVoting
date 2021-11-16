package com.CS495.RankChoiceVoting.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CS495.RankChoiceVoting.DataTransferObjects.BallotDTO;
import com.CS495.RankChoiceVoting.Model.Ballot;


public interface BallotService {
	
	void castBallot(BallotDTO ballot, String pollCode); //will receive ballotDTO from a post to /api/poll/{pollCode}/vote, holding ballot with just the ranking and other members like name of submitter. 
	//Then we will access object, add pollCode we got from url to the ballot's parentPollCode, save to ballot repo, then add ballot code that has been randomly generated to the poll's List<String>ballotCodes 
	void castAllBallots( List<String> ballots); //cast aggregated list of ballotID's to polls' list<ballot>'s <-- not gonna be in the box for final submission
	void createBallot(BallotDTO ballot); //going to commented out once i see if i can encapsulate everything into cast ballot
}