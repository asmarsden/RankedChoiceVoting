package com.CS495.RankChoiceVoting.Services;

import com.CS495.RankChoiceVoting.DataTransferObjects.PollDTO;

public interface PollService {
	//crud operations for polls
	public PollDTO findPollByUrlCode (String urlCode);
	
	public PollDTO createPoll (PollDTO pollDTO);
	
	public PollDTO updatePoll (PollDTO pollDTO); //why is one named pollDTO and not the other? <-- no reason
	
	public PollDTO updatePollBallots (PollDTO pollDTO, String ballotCode);

	public void deletePoll (PollDTO pollDTO);
	
	public PollDTO endPoll (String pollCode);
	
	
		
}
