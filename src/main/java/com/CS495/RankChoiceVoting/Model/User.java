package com.CS495.RankChoiceVoting.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class User{
	
	@Id
	public String userID;
	
	public String userName;
	
	@Override
	public String getUsername()
	{
		return username;
	}
	@Override
	public String getUserID()
	{
		return userID;
	}
}