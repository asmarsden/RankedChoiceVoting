package com.CS495.RankChoiceVoting.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CS495.RankChoiceVoting.Services.VoteService;

@RestController
@RequestMapping("api/vote")
@CrossOrigin
public class VoteController {
	
	@Autowired
	public VoteService voteService;
	
	@PostMapping("/{vID}")
	public void vote( @PathVariable ("vID") String vID) 
	{
		voteService.vote( vID);
	}
	
	@PutMapping
	public void putAllVotes (@RequestBody List<String> voteCodes /*, http servlet request to grab ip for future purposes*/)
	{
		voteService.putAllVotes(voteCodes /*request.getRemoteAddr(); */);
	}
}