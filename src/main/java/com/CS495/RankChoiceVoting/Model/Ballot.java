package com.CS495.RankChoiceVoting.Model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.data.annotation.Id;

@Component
@Document("Ballots")
public class Ballot {

	@Id
    private String ballotId; //for mongo
    private String name;
    private int[] ranking;
    private String ballotCode;
    private String parentPollCode;
    
    
	public String getParentPollCode() {
		return parentPollCode;
	}
	public void setParentPollCode(String parentPollCode) {
		this.parentPollCode = parentPollCode;
	}
	public String getBallotCode() {
		return ballotCode;
	}
	public void setBallotCode(String ballotCode) {
		this.ballotCode = ballotCode;
	}
	public String getId() {
        return ballotId;
    }
    public void setId(String ballotId) {
        this.ballotId = ballotId;
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
}
