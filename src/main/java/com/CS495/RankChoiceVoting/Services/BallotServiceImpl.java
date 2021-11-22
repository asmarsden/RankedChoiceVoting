package com.CS495.RankChoiceVoting.Services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CS495.RankChoiceVoting.Repository.PollRepository;
import com.CS495.RankChoiceVoting.mappers.BallotMapper;
import com.CS495.RankChoiceVoting.mappers.PollMapper;
import com.CS495.RankChoiceVoting.Repository.BallotRepository;
import com.CS495.RankChoiceVoting.DataTransferObjects.BallotDTO;
import com.CS495.RankChoiceVoting.DataTransferObjects.PollDTO;
import com.CS495.RankChoiceVoting.Model.Ballot;
import com.CS495.RankChoiceVoting.Model.Poll;


@Service
public class BallotServiceImpl implements BallotService {
	
	@Autowired
	private BallotRepository ballotRepository;
	
	@Autowired
	private PollRepository pollRepository;
	
	@Autowired
	private BallotMapper ballotMapper;
	@Autowired
	private PollMapper pollMapper;
	
	@Autowired
	private PollService pollService;
	
	@Override
	public void createBallot(BallotDTO ballot) //will need to be a dto prob
	{
		Ballot ballotToSave = new Ballot();
		ballotToSave=ballotMapper.ballotDTOtoBallot(ballot);
		ballotRepository.save(ballotToSave);
	}
	@Override
	public void castBallot(BallotDTO ballot, String pollCode)
	{
		//before we cast ballot, it must be created and saved in repository
		Ballot ballotToCast = new Ballot();
		//Optional<Ballot> optBallot = ballotRepository.findByBallotCode(ballotCode);
		//Ballot grabbedBallot = optBallot.get();
		Poll poll = pollRepository.findByUrlCode(pollCode); //passed in by the controller from a post request @/api/poll/"{pollcode}"/vote. The current poll
		ballotToCast = ballotMapper.ballotDTOtoBallot(ballot);
		ballotToCast.setParentPollCode(pollCode);
		ballotToCast.setBallotCode(generateRandomBallotID());
		ballotRepository.save(ballotToCast);
		//poll.appendBallot(ballotToCast.getBallotCode());
		PollDTO pollToUpdate = pollMapper.polltoDTO(poll);
		pollService.updatePollBallots(pollToUpdate, ballotToCast.getBallotCode());
	}
	
	@Override
	public String returnBallotName(String ballotCode)
	{
		Ballot ballot = ballotRepository.findByBallotCode(ballotCode);
		String name = ballot.getName();
		return name;
	}
	
	
	@Override
	public void deleteBallot(String urlCode, String adminCode, String nameOnBallot) { //probably not going to make it in the final product
		// TODO Auto-generated method stub
		
	}
	
	public String generateRandomBallotID() {
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