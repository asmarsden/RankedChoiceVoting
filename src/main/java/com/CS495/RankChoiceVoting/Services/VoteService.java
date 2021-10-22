package com.CS495.RankChoiceVoting.Services;

import java.util.List;

public interface VoteService {
	
	void vote ( String vID);
	
	void putAllVotes( List<String> voteCodes /*, String ip */);
}