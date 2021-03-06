package com.CS495.RankChoiceVoting.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CS495.RankChoiceVoting.DataTransferObjects.PollDTO;
import com.CS495.RankChoiceVoting.Services.PollService;


@RestController
@RequestMapping("/api/poll")
@CrossOrigin
public class PollController {
	
	@Autowired
	public PollService pollService;
	
	
	 @GetMapping ("/{code}")
     public PollDTO getPollByCode ( @PathVariable String code )
     {
             return pollService.findPollByCode( code );
     }
	 
	@PostMapping
	public PollDTO initializePoll(@RequestBody PollDTO poll)
	{
		return pollService.createPoll(poll);
	}
	
	
	
	
	
	
}


