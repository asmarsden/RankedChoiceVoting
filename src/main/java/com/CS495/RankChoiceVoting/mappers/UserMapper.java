package com.CS495.RankChoiceVoting.mappers;


import org.mapstruct.Mapper;

import com.CS495.RankChoiceVoting.DataTransferObjects.UserDTO;
import com.CS495.RankChoiceVoting.Model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	User userDTOtoUser (UserDTO UserDTO);
	UserDTO usertoDTO (User user);
}