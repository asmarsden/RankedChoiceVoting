package com.CS495.RankChoiceVoting.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;


import com.CS495.RankChoiceVoting.Model.Ballot;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BallotDTO {
	
	//private String ballotId; //for mongo
    private String name;
    private int[] ranking;
    private String ballotCode;
    private String parentPollCode; //<--maybe set this in createBallot? we need to have something that links the created ballot to the poll it refers to. 
    //I guess with the "link" that gets sent to ppl to vote on the poll, we'll have the poll object fetched so we can have the pollCode available, but thats only available on the backend
    //so then i can find the poll in pollRepository.findByPollCode(parentPollCode) in the service to append the ballot to the poll list
    
    
	public String getParentPollCode() {
		return parentPollCode;
	}
	public void setParentPollCode(String parentPollCode) {
		this.parentPollCode = parentPollCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getRanking() {
		return ranking;
	}
	public void setRanking(int[] ranking) {
		this.ranking = ranking;
	}
	public String getBallotCode() {
		return ballotCode;
	}
	public void setBallotCode(String ballotCode) {
		this.ballotCode = ballotCode;
	}
    
    
	
}