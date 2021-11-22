package com.CS495.RankChoiceVoting.Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CS495.RankChoiceVoting.DataTransferObjects.PollDTO;
import com.CS495.RankChoiceVoting.Model.Ballot;
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
	@Autowired
	private BallotService ballotService;
	

	
	@Override
	public PollDTO getPoll(String urlCode) { //send back poll status, question, candidates, name and password requirement flag.
		if (pollRepository.existsByUrlCode(urlCode))
		{
			Poll pollToSend = pollRepository.findByUrlCode(urlCode);
			pollToSend.setBallots(null);//take away the ballots from the DTO to send back, "private info"
			pollToSend.setAdminCode(null);//dont send the admin code back either
			return pollMapper.polltoDTO(pollToSend);
		}
		return null;
	}

	@Override
	public List<String> getNames(String urlCode, String adminCode)
	{
		List<String> names = new ArrayList<String>();
		//List<List<String>> names = new ArrayList<List<String>>();
		
		if(pollRepository.existsByUrlCode(urlCode))
		{
			Poll pollToGrabNamesFrom = pollRepository.findByUrlCode(urlCode);
			if(pollToGrabNamesFrom.getAdminCode().equals(adminCode)) {
			List<String> ballotCodes = pollToGrabNamesFrom.getBallots();
			//Ballot ballotToGrabName = new Ballot();
			ballotCodes.stream()
			.forEach(e -> {
				 names.add(ballotService.returnBallotName(e));
			} );
			}
			return names;
		}
		
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
	public PollDTO endPoll(String urlCode, String adminCode)
	{
		if (pollRepository.existsByUrlCode(urlCode))
		{
			Poll pollToEnd = pollRepository.findByUrlCode(urlCode);
			if(pollToEnd.getAdminCode() == adminCode) {
			pollToEnd.setStatus(false);
			//pollMapper.polltoDTO(pollToEnd);
			pollRepository.save(pollToEnd);
			return pollMapper.polltoDTO(pollToEnd);
			}
			
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
