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
import com.CS495.RankChoiceVoting.Repository.BallotRepository;
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
	@Autowired 
	private BallotRepository ballotRepository;
	@Autowired
	private PollService pollService;
	

	
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
		pollToSaveToDatabase.setUrlCode(generateRandomUrlCode());
		//pollToSaveToDatabase.setStatus(true);//generate random code
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
		System.out.println("endpoll start");
		if (pollRepository.existsByUrlCode(urlCode))
		{
			System.out.println("endpoll start inside if");
			Poll pollToEnd = pollRepository.findByUrlCode(urlCode);
			System.out.println(pollToEnd.getAdminCode());
			if(pollToEnd.getAdminCode().equals(adminCode)) {
			pollToEnd.setStatus(false);
			System.out.println("set status tofalse, trying to calc winner");
			int indexOfWinner = pollService.calculateWinner(urlCode);
			String[] candidates = pollToEnd.getCandidates();
			String winner = candidates[indexOfWinner];
			pollToEnd.setWinner(winner);
			pollRepository.save(pollToEnd);
			return pollMapper.polltoDTO(pollToEnd);
			}
			
		}
		return null;
	}
	
	@Override
	public int calculateWinner(String urlCode) { //returns the index of a winner inside Poll's candidate array
		System.out.println("calcing winner start");

		Poll pollToFindWinner = pollRepository.findByUrlCode(urlCode);
	    int numCandidates = pollToFindWinner.getCandidates().length; // FIXME! should get the actual number of candidates here
	    int numVotes = pollToFindWinner.getBallots().size(); // FIXME! should get the actual number of votes here
	    List<String> ballots = pollToFindWinner.getBallots();// FIXME! populate this temporary list with the ballots

	    boolean[] isCandidateInTheRunning = new boolean[numCandidates];
	    for (int i = 0; i < numCandidates; i++) {
	        isCandidateInTheRunning[i] = true;
	    }
	    
	    //initialize the tally - an array of ints that we will use to find the possible winner
	    int[] tally = new int[numCandidates];

	    //repeat until a single candidate reaches at least 50% of the vote
	    while (true) {

	        //wipe tally
	        for (int i = 0; i < numCandidates; i++) {
	            tally[i] = 0;
	        }

	        //count up the tally
	        for (int ballotIdx = 0; ballotIdx < ballots.size(); ballotIdx++) { //iterate over the ballotCodes array 
	        	Ballot ballot = ballotRepository.findByBallotCode(ballots.get(ballotIdx)); //set current ballot to the corresponding indexed ballot
	            int i = 0; //used to return keep track of what candidate index we're on ? not needed i dont think
	            int[] currentBallotRanks = ballot.getRanking();
	            //this looks at the ballot's first choice and checks if the corresponding candidate is NOT in the running. if it is NOT, iterate to the user's second choice, etc.
	            while (!isCandidateInTheRunning[i]) {
	                i++;
	            }
	            
	            boolean noone = false;
	            if(i >= currentBallotRanks.length) { //index out of bounds check, set back down to its last one, and means no one in thats valid
	            	i--;
	            	noone = true;
	            }
	            
	            if(noone == false)
	            	tally[currentBallotRanks[i]] += 1;
	            
	          int candidateInTheRunningCount = 0;
				if (currentBallotRanks.length > 0) {// only try to access arrays if a ballot is valid by having at least 1
													// candidate chosen
					for (int j = 0; j < currentBallotRanks.length; j++) // iterating over the current Ballot's ranking array
					{ // increasing the tally at the index if the candidate is still in the running.
						if (isCandidateInTheRunning[currentBallotRanks[j]])  //was previously isCandidateInTheRunning[j]
							candidateInTheRunningCount += 1;//tally[currentBallotRanks[j]] += 1; //was previously [j]	
					}

					if (candidateInTheRunningCount == 0) // if no more candidates in a ballot's choices are still in the// running, "remove" the ballot and decrement numVotes;								
					{
						ballots.remove(ballot.getBallotCode());
						numVotes -= 1;
					}
				}

	            
	            // FIXME! if you check through and all candidates are out of the running, remove the ballot from the "ballots" list, and do numVotes--;
	            // also remember to check to make sure you're not accessing the array out of bounds, because a ballot's ranking is not always complete (can even have no ranking)
	            //tally[ballot.ranking[i]]++;
	        }

	        //find the index with the most votes
	        int mostPopular = 0; //index of most popular candidate initialized at beginning of candidate list
	        for (int i = 0; i < numCandidates; i++) {
	            if (tally[i] > tally[mostPopular])
	            mostPopular = i; //update index if it has more votes in the tally array at that index
	        }

	        //check if winner (requires 50% or more)
	        float currentVote50Percentage = numVotes/2;
	        if (tally[mostPopular] >= currentVote50Percentage) {
	        	System.out.println("returning index of calculated Winner..");
	            return mostPopular;
	        }
	        else {
	            //no winner was found, eliminate the least popular candidate
	        	System.out.println("no winner this time");
	            int leastPopular = 0;
	            //iterates to the first eligible candidate
	            while (!isCandidateInTheRunning[leastPopular]) {
	                leastPopular++;
	            }
	            for (int i = 0; i < numCandidates; i++) {
	                if (isCandidateInTheRunning[i] && tally[i] < tally[leastPopular])
	                    leastPopular = i;
	            }
	            isCandidateInTheRunning[leastPopular] = false;
	        }
	    }

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
