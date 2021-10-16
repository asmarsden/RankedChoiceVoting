package com.CS495.RankChoiceVoting.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Locale;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Polls")
public class Poll {

  @Id
  public String pollID; //for mongo purposes

  public String pollQuestion; //Title of poll to be voted on
  public String askedBy; //creatorID / userID
  public boolean require_name;
  public String createdAt;
  public List<Vote> voteList;
  
  public String pollCode; //for our identifying purposes

  public Poll() {}

  public Poll(String pollQuestion, String askedBy, List<Vote> votes) {
    this.pollQuestion = pollQuestion;
    this.askedBy = askedBy;
    this.voteList = votes;
    //this.createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }
  
  

  public String getPollQuestion() {
	return pollQuestion;
}

public void setPollQuestion(String pollQuestion) {
	this.pollQuestion = pollQuestion;
}

public String getAskedBy() {
	return askedBy;
}

public void setAskedBy(String askedBy) {
	this.askedBy = askedBy;
}

public boolean isRequire_name() {
	return require_name;
}

public void setRequire_name(boolean require_name) {
	this.require_name = require_name;
}

public String getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(String createdAt) {
	this.createdAt = createdAt;
}

public List<Vote> getVoteList() {
	return voteList;
}

public void setVoteList(List<Vote> voteList) {
	this.voteList = voteList;
}

public String getPollCode() {
	return pollCode;
}

public void setPollCode(String pollCode) {
	this.pollCode = pollCode;
}



@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Poll other = (Poll) obj;
	return require_name == other.require_name;
}

@Override
  public String toString() {
    return String.format(
    "Poll[PollID=%s, pollQuestion='%s', askedBy='%s', createdAt='%s']",
        pollID, pollQuestion, askedBy, createdAt);
  }

}