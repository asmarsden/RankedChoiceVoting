package com.CS495.RankChoiceVoting.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class User{
	
	@Id
	public String userID;
	
	public String userName;
	private String password;
	

	public String getUsername()
	{
		return userName;
	}

	public String getUserID()
	{
		return userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
}
