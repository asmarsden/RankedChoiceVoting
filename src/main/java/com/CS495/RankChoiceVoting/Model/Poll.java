package com.CS495.RankChoiceVoting.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Locale;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document("Polls")
public class Poll {

  @Id
  private String pollId; //for mongo purposes
  private String creatorId;
  private String urlCode;
  private String question; //Title of poll to be voted on
  private String endTime;
  //public String askedBy; //creatorID / userID
  private boolean requireName;
  private boolean status = true;
  private boolean isStatus() {
	return status;
}



private String password; //null if no password is required
  private String[] candidates;
  private List<String> ballots;
  //public String createdAt;
  //public List<Vote> voteList;
  
  //public String pollCode; //for our identifying purposes

  public Poll() {}

//  public Poll(String pollQuestion, String askedBy, List<Vote> votes) {
//    this.pollQuestion = pollQuestion;
//    this.askedBy = askedBy;
//    this.voteList = votes;
//    //this.createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//  }
//  
  
  public void setStatus(boolean status) {
		this.status = status;
	}
  
  public void appendBallot(String ballotCode) {
	  this.ballots.add(ballotCode);
  }
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
	
	

	//@Override
//public boolean equals(Object obj) {
	//	if (this == obj)
		//		return true;
	//	if (obj == null)
		//		return false;
	//	if (getClass() != obj.getClass())
		//		return false;
	//	Poll other = (Poll) obj; //confused about these two lines
	//	//return requireName == other.requireName;
//}

public List<String> getBallots() {
		return ballots;
	}

	public void setBallots(List<String> ballots) {
		this.ballots = ballots;
	}

@Override
  public String toString() { //missing candidates and ballots for now
    return String.format(
    "Poll[poll_id=%s, creator_id='%s', url_code='%s', question='%s', end_time='%s', require_name='%s', password='%s']",
    pollId, creatorId, urlCode, question, endTime, requireName, password);
  }

}