package com.CS495.RankChoiceVoting.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Users")
public class User{
	
	@Id
	public String userID;
	
	public String userName;
	
	public String password;
	
	public List<String> userPolls; 
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void appendPollList(String pollId) {
		this.userPolls.add(pollId);
	}
	
}