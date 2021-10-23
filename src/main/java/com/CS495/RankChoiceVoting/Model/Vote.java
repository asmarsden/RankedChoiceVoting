package com.CS495.RankChoiceVoting.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Vote")
public class Vote {
	
	@Id
	public String voteID;
	
	public String voteName;
	
	public int voteCount;
	
	public String voteCode;
	
	public Vote() {}

	public Vote(String voteID, String voteName, int voteCount, String voteCode) {
		this.voteID = voteID;
		this.voteName = voteName;
		this.voteCount = voteCount;
		this.voteCode = voteCode;
	}

	public String getVoteID() {
		return voteID;
	}

	public void setVoteID(String voteID) {
		this.voteID = voteID;
	}

	public String getVoteName() {
		return voteName;
	}

	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String getVoteCode() {
		return voteCode;
	}

	public void setVoteCode(String voteCode) {
		this.voteCode = voteCode;
	}
	
	
	
	
	
	
	
	
}