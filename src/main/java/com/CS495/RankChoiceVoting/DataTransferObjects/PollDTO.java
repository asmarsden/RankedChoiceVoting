package com.CS495.RankChoiceVoting.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.CS495.RankChoiceVoting.Model.Ballot;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollDTO
{
	//private String pollId;
    private String adminCode;
    private String urlCode;
    private String question;
    private String endTime; //java.time.LocalDateTime
    private boolean requireName;
    private String password;
    private String[] candidates;
    private List<String> ballots = new ArrayList<String>();;
    private boolean status = true;
    private String winner = "";

   


    public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getAdminCode() {
		return adminCode;
	}
	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}
	

    public String getUrlCode() {
        return urlCode;
    }
    public void setUrlCode(String urlCode) {
        this.urlCode = urlCode;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isRequireName() {
        return requireName;
    }
    public void setRequireName(boolean requireName) {
        this.requireName = requireName;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getCandidates() {
        return candidates;
    }
    public void setCandidates(String[] candidates) {
        this.candidates = candidates;
    }

    public List<String> getBallots() {
        return ballots;
    }
    public void setBallots(List<String> ballots) {
        this.ballots = ballots;
    }
}
