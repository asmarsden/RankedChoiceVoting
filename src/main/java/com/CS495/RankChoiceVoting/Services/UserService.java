package com.CS495.RankChoiceVoting.Services;

//import org.springframework.security.core.userdetails.UserDetailsService;

import com.CS495.RankChoiceVoting.DataTransferObjects.UserDTO;
import com.CS495.RankChoiceVoting.Model.User;

public interface UserService 
{
	//Changed from User create ( UserDTO userDTO);
	User create(UserDTO userDTO);
	
}