package com.CS495.RankChoiceVoting.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.CS495.RankChoiceVoting.DataTransferObjects.UserDTO;
import com.CS495.RankChoiceVoting.Model.User;
import com.CS495.RankChoiceVoting.Repository.PollRepository;
import com.CS495.RankChoiceVoting.Repository.UserRepository;
import com.CS495.RankChoiceVoting.mappers.UserMapper;

//import javax.validation.constraints.NotNull;
import java.util.Collections;

@Service ("userService")
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PollRepository pollRespository;
	
	
	@Override
	@Transactional
	public User create (UserDTO userDTO)
	{
		if ( !StringUtils.isEmpty( userDTO.getUserName() ) &&
                userRepository.findUserByUserName( userDTO.getUserName() ) != null )
			
                throw new RuntimeException( "User already exists" );
		User userToSave = new User();
		userToSave = userMapper.userDTOtoUser(userDTO);
		userToSave.setUserName(userDTO.getUserName());
		User savedUser = userRepository.save(userToSave);
		return savedUser;
	}
	public void addPolltoUserList(String pollId) 
	{
		//Still working
		//Use findbyPollId to get correct pollId and then use
		//appendPollList to add pollId to the list
		
	}
	
}
