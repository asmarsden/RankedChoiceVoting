package com.CS495.RankChoiceVoting.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import com.CS495.RankChoiceVoting.Model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private String userName;
	private String password;
	
	//Added
	public UserDTO() {}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}