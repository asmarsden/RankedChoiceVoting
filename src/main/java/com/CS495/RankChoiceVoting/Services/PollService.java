package com.CS495.RankChoiceVoting.Services;

import com.CS495.RankChoiceVoting.DataTransferObjects.PollDTO;

public interface PollService {
	//crud operations for polls
	PollDTO findPollByCode (String pollCode);
	
	PollDTO createPoll (PollDTO poll);
	
	PollDTO updatePoll (PollDTO pollDTO);

	void deletePoll(PollDTO pollDTO);
	
	//findby user
	//find all polls -> List<PollDTO>
	//updatePoll 
	
	
	
}