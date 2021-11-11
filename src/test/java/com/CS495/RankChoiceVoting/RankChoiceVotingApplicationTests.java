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
	PollDTO dto;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void createPollTest() throws Exception {
		String[] bal = {"Wendy's", "Arby's", "McDonald's", "Burger King", "KFC", "Taco Bell"};
		poll.setCreatorId("asmarsden");
		poll.setQuestion("Where should we get dinner?");
		poll.setCandidates(bal);
		dto.polltoDTO(poll); //hmm its having trouble finding this for some reason
		dto.createPoll(dto);
		//does this return the poll code? i have it set so it makes the poll code here but. hm
		code = dto.getUrlCode();
		

	}
	
	@Test
	void getPollCode() throws Exception {
		//i can't really write this until i know how the ballot is going to look data-wise
		dto.getPollByCode(code);
	}
	
	@Test
	void voteTest1() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test1");
		int[] ranks = {1, 2, 3};
		bal.setRanking(ranks);
		BallotDTO balDTO = new BallotDTO();
		balDTO.ballottoDTO(bal);
		//still dont know if im doing this right
		dto.updatePoll();//something something ill fix this later
	}
	
	@Test
	void voteTest2() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test2");
		int[] ranks = {1, 2, 3};
		bal.setRanking(ranks);
	}
	@Test
	void voteTest3() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test3");
		int[] ranks = {3, 2, 1};
		bal.setRanking(ranks);
	}
	@Test
	void voteTest4() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test4");
		int[] ranks = {4, 3, 2};
		bal.setRanking(ranks);
	}
	@Test
	void voteTest5() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test5");
		int[] ranks = {4, 1};
		bal.setRanking(ranks);
	}
	@Test
	void voteTest6() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test6");
		int[] ranks = {1, 4, 5, 3, 2};
		bal.setRanking(ranks);
	}
	@Test
	void voteTest7() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test7");
		int[] ranks = {1, 2, 5};
		bal.setRanking(ranks);
	}
	@Test
	void voteTest8() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test8");
		int[] ranks = {3, 4};
		bal.setRanking(ranks);
	}
	@Test
	void voteTest9() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test9");
		int[] ranks = {1};
		bal.setRanking(ranks);
	}
	@Test
	void voteTest10() throws Exception {
		Ballot bal = new Ballot();
		bal.setParentPollCode(code);
		bal.setName("test10");
		int[] ranks = {4, 5, 3};
		bal.setRanking(ranks);
	}
	@Test
	void closePollTest() throws Exception {
		//i dont see a function for this? i guess thatll be added with the calculate results stuff
	}
	@Test
	void calculateVoteResultsTest() throws Exception {
		//this function has not been finished
	}
	@Test
	void deletePollTest() throws Exception {
		deletePoll(dto); 
	}
	
}
