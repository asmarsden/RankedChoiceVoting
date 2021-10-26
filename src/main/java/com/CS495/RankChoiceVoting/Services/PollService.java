package com.CS495.RankChoiceVoting.Services;

import com.CS495.RankChoiceVoting.DataTransferObjects.PollDTO;

public interface PollService {
	//crud operations for polls
	public PollDTO findPollByUrlCode (String urlCode);
	
	public PollDTO createPoll (PollDTO poll);
	
	public PollDTO updatePoll (PollDTO pollDTO); //why is one named pollDTO and not the other?

	public void deletePoll (PollDTO pollDTO);
	
	//findby user
	//find all polls -> List<PollDTO>
	//updatePoll 
	
		
}
