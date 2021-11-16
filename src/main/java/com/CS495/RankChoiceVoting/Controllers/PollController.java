package com.CS495.RankChoiceVoting.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CS495.RankChoiceVoting.DataTransferObjects.PollDTO;
import com.CS495.RankChoiceVoting.Repository.PollRepository;
import com.CS495.RankChoiceVoting.Services.PollService;


@RestController
@RequestMapping("/api/poll")
@CrossOrigin
public class PollController {
	
	@Autowired
	private PollService pollService;
	
	@Autowired
	private PollRepository pollRepository;
	
	
	 @GetMapping ("/{code}")
     public PollDTO getPollByCode ( @PathVariable("code") String code )
     {
             return pollService.findPollByUrlCode( code );
     }
	 
	 @PostMapping ("/{code}/end")
	 public PollDTO endPoll (@PathVariable("code") String code)
	 {
		 return pollService.endPoll(code);
	 }
	 
	@PostMapping
	public PollDTO initializePoll(@RequestBody PollDTO poll)
	{
		return pollService.createPoll(poll);
	}
	
	
}


