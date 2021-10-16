package com.CS495.RankChoiceVoting.Services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CS495.RankChoiceVoting.DataTransferObjects.PollDTO;
import com.CS495.RankChoiceVoting.Model.Poll;
import com.CS495.RankChoiceVoting.Repository.PollRepository;
import com.CS495.RankChoiceVoting.Repository.VoteRepository;
import com.CS495.RankChoiceVoting.mappers.PollMapper;

@Service
public class PollServiceImpl implements PollService {

	@Autowired
	private PollRepository pollRepository;
	@Autowired
	private PollMapper pollMapper;
	@Autowired
	private VoteRepository voteRepository;
	
	//@Autowired
    //public PollServiceImpl ( PollRepository pollRepository, PollMapper pollMapper, VoteRepository voteRepository )
    //{
    //        this.pollRepository = pollRepository;
    //        this.pollMapper = pollMapper;
    //        this.voteRepository = voteRepository;
    //}
	
	@Override
	public PollDTO findPollByCode(String pollCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public PollDTO createPoll( PollDTO poll) {

		Poll pollToSaveToDatabase = new Poll(); 
		pollToSaveToDatabase = pollMapper.pollDTOtoPoll(poll);
		System.out.println(poll.getVoteList());
		System.out.println(pollToSaveToDatabase.getPollQuestion());
		System.out.println("question above this and vote list below this");
		System.out.println(pollToSaveToDatabase.getVoteList());
		
		//todo: if security checks out, then entry
		pollToSaveToDatabase.setPollCode("12345"); //generate random code
		pollToSaveToDatabase.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		pollToSaveToDatabase.setAskedBy("currentUser"); //add .getUser.userDTOtoUser.getUsername()
		pollToSaveToDatabase.setRequire_name(false); //pollToSaveToDatabase.equals(pollToSaveToDatabase) or something
		pollToSaveToDatabase.getVoteList().forEach( e -> e.setVoteCode("54321"));//generate random code
		
		Poll savedPoll = pollRepository.save(pollToSaveToDatabase);
		return pollMapper.polltoDTO( savedPoll);
	}

	@Override
	public PollDTO updatePoll(PollDTO pollDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//define the operations listed in PollService
	
	
	
}
