package com.CS495.RankChoiceVoting.mappers;


import org.mapstruct.Mapper;

import com.CS495.RankChoiceVoting.DataTransferObjects.BallotDTO;
import com.CS495.RankChoiceVoting.Model.Ballot;

@Mapper(componentModel = "spring")
public interface BallotMapper {
	
	Ballot ballotDTOtoBallot (BallotDTO ballotDTO);
	BallotDTO ballottoDTO (Ballot ballot);
}
