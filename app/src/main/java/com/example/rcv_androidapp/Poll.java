package com.example.rcv_androidapp;

import java.util.List;

public class Poll {

    private String pollId;
    private String adminCode;
    private String urlCode;
    private String question;
    private boolean status;
    private boolean requireName;
    private String password;
    private String[] candidates;
    private List<Ballot> ballots;
    private String winner;

    public String getPollId() {
        return pollId;
    }
    public void setPollId(String poll_id) {
        this.pollId = poll_id;
    }

    public String getAdminCode() {
        return adminCode;
    }
    public void setAdminCode(String admin_code) {
        this.adminCode = admin_code;
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

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean bool) {
        status = bool;
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

    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
}
