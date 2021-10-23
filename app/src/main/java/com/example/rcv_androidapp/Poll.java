package com.example.rcv_androidapp;

import java.util.List;

public class Poll {

    private String poll_id;
    private String creator_id;
    private String question;
    private boolean require_name;
    private String password;
    private List<String> candidates;
    private List<Ballot> ballots;

    public String getPoll_id() {
        return poll_id;
    }
    public void setPoll_id(String poll_id) {
        this.poll_id = poll_id;
    }

    public String getCreator_id() {
        return creator_id;
    }
    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isRequire_name() {
        return require_name;
    }
    public void setRequire_name(boolean require_name) {
        this.require_name = require_name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getCandidates() {
        return candidates;
    }
    public void setCandidates(List<String> candidates) {
        this.candidates = candidates;
    }

    public List<Ballot> getBallots() {
        return ballots;
    }
    public void setBallots(List<Ballot> ballots) {
        this.ballots = ballots;
    }
}
