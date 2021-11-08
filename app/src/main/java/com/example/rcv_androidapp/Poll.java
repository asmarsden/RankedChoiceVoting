package com.example.rcv_androidapp;

import java.util.List;

public class Poll {

    private String pollId;
    private String creatorId;
    private String urlCode;
    private String question;
    private boolean isActive;
    private String endTime; //java.time.LocalDateTime
    private boolean requireName;
    private String password;
    private String[] candidates;
    private List<Ballot> ballots;

    public String getPollId() {
        return pollId;
    }
    public void setPollId(String poll_id) {
        this.pollId = poll_id;
    }

    public String getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(String creator_id) {
        this.creatorId = creator_id;
    }

    public String getUrlCode() {
        return urlCode;
    }
    public void setUrlCode(String url_code) {
        this.urlCode = url_code;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String end_time) {
        this.endTime = end_time;
    }

    public boolean isRequireName() {
        return requireName;
    }
    public void setRequireName(boolean require_name) {
        this.requireName = require_name;
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
