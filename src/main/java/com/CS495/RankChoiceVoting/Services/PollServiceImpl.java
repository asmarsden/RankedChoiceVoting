package com.CS495.RankChoiceVoting.Services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CS495.RankChoiceVoting.DataTransferObjects.PollDTO;
import com.CS495.RankChoiceVoting.Model.Poll;
import com.CS495.RankChoiceVoting.Repository.PollRepository;
//import com.CS495.RankChoiceVoting.Repository.VoteRepository;
import com.CS495.RankChoiceVoting.mappers.PollMapper;

@Service
public class PollServiceImpl implements PollService {

	@Autowired
	private PollRepository pollRepository;
	@Autowired
	private PollMapper pollMapper;
	//@Autowired
	//private VoteRepository voteRepository;
	
	//@Autowired
    //public PollServiceImpl ( PollRepository pollRepository, PollMapper pollMapper, VoteRepository voteRepository )
    //{
    //        this.pollRepository = pollRepository;
    //        this.pollMapper = pollMapper;
    //        this.voteRepository = voteRepository;
    //}
	
	@Override
	public PollDTO findPollByUrlCode(String pollCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public PollDTO createPoll( PollDTO poll) {

		Poll pollToSaveToDatabase = new Poll(); 
		pollToSaveToDatabase = pollMapper.pollDTOtoPoll(poll);
		//todo: if security checks out, then entry
		pollToSaveToDatabase.setUrlCode(generateRandomUrlCode()); //generate random code
		Poll savedPoll = pollRepository.save(pollToSaveToDatabase);
		return pollMapper.polltoDTO( savedPoll);
	}

	@Override
	public PollDTO updatePoll(PollDTO pollDTO) {
		if (pollRepository.existsByUrlCode(pollDTO.getUrlCode()))
		{
			Poll pollToUpdate = pollRepository.findByUrlCode(pollDTO.getUrlCode());
			//pollToUpdate.setRequireName(pollDTO.isRequireName());
			//other members that we would like to be able to change as we add for RCV

			return pollMapper.polltoDTO(pollRepository.save(pollToUpdate));
		}
		return null;
	}
	
	@Override
	public PollDTO updatePollBallots(PollDTO pollDTO, String ballotCode)
	{
		if (pollRepository.existsByUrlCode(pollDTO.getUrlCode()))
		{
			Poll pollToUpdate = pollRepository.findByUrlCode(pollDTO.getUrlCode());
			pollToUpdate.appendBallot(ballotCode);
			return pollMapper.polltoDTO(pollRepository.save(pollToUpdate));
		}
		return null;
	}
	
	@Override
	public void deletePoll(PollDTO pollDTO)
	{
		if (pollRepository.existsByUrlCode(pollDTO.getUrlCode()))
		{
			Poll pollToDelete = pollRepository.findByUrlCode(pollDTO.getUrlCode());
			pollRepository.deleteByUrlCode(pollToDelete.getUrlCode());
		}
	}
	
	@Override
	public PollDTO endPoll(String pollCode)
	{
		if (pollRepository.existsByUrlCode(pollCode))
		{
			Poll pollToEnd = pollRepository.findByUrlCode(pollCode);
			pollToEnd.setStatus(false);
			//pollMapper.polltoDTO(pollToEnd);
			pollRepository.save(pollToEnd);
			return pollMapper.polltoDTO(pollToEnd);
			
		}
		return null;
	}

	public String generateRandomUrlCode() { // difference is poll = length 8, vote length 10
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 8;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    //System.out.println(generatedString);
	    return generatedString;
	}
	
	public String generateRandomVoteID() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    //System.out.println(generatedString);
	    return generatedString;
	}
	
	
}
