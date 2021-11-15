package com.CS495.RankChoiceVoting;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mongounit.AssertMatchesDataset;
import org.mongounit.LocationType;
import org.mongounit.MongoUnit;
import org.mongounit.MongoUnitTest;
import org.mongounit.SeedWithDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.CS495.RankChoiceVoting.DataTransferObjects.*;
import com.CS495.RankChoiceVoting.Model.*;
import com.CS495.RankChoiceVoting.Controllers.*;
import com.CS495.RankChoiceVoting.mappers.*;
import com.CS495.RankChoiceVoting.Repository.*;
import com.CS495.RankChoiceVoting.Services.*;

@SpringBootTest
@MongoUnitTest
@DisplayName("Testing the Ranked Choice Voting Application with MongoUnit testing framework")
class RankChoiceVotingApplicationTests {
	
	@Autowired
	private Poll poll;
	private String code;
	PollDTO pollDTO;
	private PollRepository repository;
	private PollMapper pollMapper;
	private PollService pollService;
	private BallotService balService;
	private BallotMapper balMapper;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@DisplayName("Testing poll creation")
	void createPollTest() throws Exception {
		String[] bal = {"Wendy's", "Arby's", "McDonald's", "Burger King", "KFC", "Taco Bell"};
		poll.setCreatorId("asmarsden");
		poll.setQuestion("Where should we get dinner?");
		poll.setCandidates(bal);
		pollDTO = pollMapper.polltoDTO(poll); 
		pollService.createPoll(pollDTO);
		//does this return the poll code? i have it set so it makes the poll code here but. hm
		code = poll.getUrlCode();
		//last thing i need to figure out is what exactly i should compare for testing, gonna look at examples later

	}
	
	@Test
	@DisplayName("Testing poll retrieval by code")
	void getPollCode() throws Exception {
		pollService.findPollByUrlCode(code);
		
	}
	
	@Test
	@DisplayName("Testing vote functionality and populating test poll for later calculations")
	void voteTest1() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test1");
		int[] ranks = {1, 2, 3};
		bal.setRanking(ranks);
		BallotDTO balDTO = balMapper.ballottoDTO(bal);
		balService.castBallot(balDTO, code);
		String balcode = balDTO.getBallotCode();
		poll.appendBallot(balcode);
		
	}
	
	@Test
	@DisplayName("Testing vote functionality and populating test poll for later calculations")
	void voteTest2() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test2");
		int[] ranks = {1, 2, 3};
		bal.setRanking(ranks);
		BallotDTO balDTO = balMapper.ballottoDTO(bal);
		balService.castBallot(balDTO, code);
		String balcode = balDTO.getBallotCode();
		poll.appendBallot(balcode);
	}
	
	@Test
	@DisplayName("Testing vote functionality and populating test poll for later calculations")
	void voteTest3() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test3");
		int[] ranks = {3, 2, 1};
		bal.setRanking(ranks);
		BallotDTO balDTO = balMapper.ballottoDTO(bal);
		balService.castBallot(balDTO, code);
		String balcode = balDTO.getBallotCode();
		poll.appendBallot(balcode);
	}
	
	@Test
	@DisplayName("Testing vote functionality and populating test poll for later calculations")
	void voteTest4() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test4");
		int[] ranks = {4, 3, 2};
		bal.setRanking(ranks);
		BallotDTO balDTO = balMapper.ballottoDTO(bal);
		balService.castBallot(balDTO, code);
		String balcode = balDTO.getBallotCode();
		poll.appendBallot(balcode);
	}
	
	@Test
	@DisplayName("Testing vote functionality and populating test poll for later calculations")
	void voteTest5() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test5");
		int[] ranks = {4, 1};
		bal.setRanking(ranks);
		BallotDTO balDTO = balMapper.ballottoDTO(bal);
		balService.castBallot(balDTO, code);
		String balcode = balDTO.getBallotCode();
		poll.appendBallot(balcode);
	}
	
	@Test
	@DisplayName("Testing vote functionality and populating test poll for later calculations")
	void voteTest6() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test6");
		int[] ranks = {1, 4, 5, 3, 2};
		bal.setRanking(ranks);
		BallotDTO balDTO = balMapper.ballottoDTO(bal);
		balService.castBallot(balDTO, code);
		String balcode = balDTO.getBallotCode();
		poll.appendBallot(balcode);
	}
	
	@Test
	@DisplayName("Testing vote functionality and populating test poll for later calculations")
	void voteTest7() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test7");
		int[] ranks = {1, 2, 5};
		bal.setRanking(ranks);
		BallotDTO balDTO = balMapper.ballottoDTO(bal);
		balService.castBallot(balDTO, code);
		String balcode = balDTO.getBallotCode();
		poll.appendBallot(balcode);
	}
	
	@Test
	@DisplayName("Testing vote functionality and populating test poll for later calculations")
	void voteTest8() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test8");
		int[] ranks = {3, 4};
		bal.setRanking(ranks);
		BallotDTO balDTO = balMapper.ballottoDTO(bal);
		balService.castBallot(balDTO, code);
		String balcode = balDTO.getBallotCode();
		poll.appendBallot(balcode);
	}
	
	@Test
	@DisplayName("Testing vote functionality and populating test poll for later calculations")
	void voteTest9() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test9");
		int[] ranks = {1};
		bal.setRanking(ranks);
		BallotDTO balDTO = balMapper.ballottoDTO(bal);
		balService.castBallot(balDTO, code);
		String balcode = balDTO.getBallotCode();
		poll.appendBallot(balcode);
	}
	
	@Test
	@DisplayName("Testing vote functionality and populating test poll for later calculations")
	void voteTest10() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test10");
		int[] ranks = {4, 5, 3};
		bal.setRanking(ranks);
		BallotDTO balDTO = balMapper.ballottoDTO(bal);
		balService.castBallot(balDTO, code);
		String balcode = balDTO.getBallotCode();
		poll.appendBallot(balcode);
	}
	
	@Test
	@DisplayName("Testing functionality of closing poll")
	void closePollTest() throws Exception {
		//i dont see a function for this? i guess thatll be added with the calculate results stuff
	}
	
	@Test
	@DisplayName("Testing result calculation functionality")
	void calculateVoteResultsTest() throws Exception {
		//this function has not been finished
	}
	
	@Test
	@DisplayName("Testing poll deletion functionality")
	void deletePollTest() throws Exception {
		pollService.deletePoll(pollDTO); 
	}
	
}
