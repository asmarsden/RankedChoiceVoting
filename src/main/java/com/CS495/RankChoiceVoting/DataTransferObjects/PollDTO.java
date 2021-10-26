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
public class PollDTO
{
	private String pollId;
    private String creatorId;
    private String urlCode;
    private String question;
    private String endTime; //java.time.LocalDateTime
    private boolean requireName;
    private boolean requirePassword;
    private String password;
    private String[] candidates;
    private List<Ballot> ballots;

    public String getPollId() {
        return pollId;
    }
    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public String getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
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

    public boolean isRequirePassword() {
        return requirePassword;
    }
    public void setRequirePassword(boolean requirePassword) {
        this.requirePassword = requirePassword;
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

    public List<Ballot> getBallots() {
        return ballots;
    }
    public void setBallots(List<Ballot> ballots) {
        this.ballots = ballots;
    }
}
