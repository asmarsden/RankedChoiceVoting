package com.CS495.RankChoiceVoting.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
//import com.CS495.RankChoiceVoting.DataTransferObjects.UserDTO;
import com.CS495.RankChoiceVoting.Model.User;
import com.CS495.RankChoiceVoting.Repository.UserRepository;

import javax.validation.constraints.NotNull;
import java.util.Collections;

@Service ("userService")
public class UserServiceImpl implements UserService
{
	@Autowired
	public UserServiceImpl (UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	@Override
	@Transactional
	public User create (@NotNull UserDTO userDTO)
	{
		if ( !StringUtils.isEmpty( userDTO.getUsername() ) &&
                userRepository.findUserByUsername( userDTO.getUsername() ) != null )
                throw new RuntimeException( "User already exists" );
		User user = User.builder().username( userDTO.getusername()).build();
		userRepository.save(user);
		return user;
	}
}