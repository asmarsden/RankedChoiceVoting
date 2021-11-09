package com.CS495.RankChoiceVoting.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CS495.RankChoiceVoting.DataTransferObjects.BallotDTO;
import com.CS495.RankChoiceVoting.Services.BallotService;

@RestController
@RequestMapping("api/poll")
@CrossOrigin
public class BallotController {
	
	@Autowired
	public BallotService ballotService;
	
	@PostMapping("/{pollCode}/vote")
	public void vote( @RequestBody BallotDTO ballot, @PathVariable("pollCode") String pollcode)
	{
		ballotService.castBallot( ballot, pollcode);
		//return ballotService.castBallot(ballotCode)
	}
	
	@PutMapping
	public void putAllVotes (@RequestBody List<String> ballotCodes /*, http servlet request to grab ip for future purposes*/)
	{
		ballotService.castAllBallots(ballotCodes /*request.getRemoteAddr(); */);
	}
}