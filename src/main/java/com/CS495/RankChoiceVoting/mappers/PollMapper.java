package com.CS495.RankChoiceVoting.mappers;


import org.mapstruct.Mapper;

import com.CS495.RankChoiceVoting.DataTransferObjects.PollDTO;
import com.CS495.RankChoiceVoting.Model.Poll;

@Mapper(componentModel = "spring")
public interface PollMapper {
	
	Poll pollDTOtoPoll (PollDTO pollDTO);
	PollDTO polltoDTO (Poll poll);
}
