package com.CS495.RankChoiceVoting.Controllers;

import java.util.List;

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
@CrossOrigin(origins = "http://localhost:4200")
public class PollController {
	
	@Autowired
	private PollService pollService;
	
	@Autowired
	private PollRepository pollRepository;
	
	@GetMapping("/{urlCode}")
	public PollDTO getPoll(@PathVariable("urlCode") String urlCode)
	{	
		return pollService.getPoll(urlCode);
	}
	
	@GetMapping(value = "/{urlCode}/{adminCode}")
	public List<String> getNames(@PathVariable("urlCode") String urlCode, @PathVariable("adminCode") String adminCode)
	{
		return pollService.getNames(urlCode, adminCode);
	}
	 
	 @PostMapping (value = "/{urlCode}/{adminCode}/end")
	 public PollDTO endPoll (@PathVariable("urlCode") String urlCode, @PathVariable("adminCode") String adminCode)
	 {
		 return pollService.endPoll(urlCode, adminCode);
	 }
	 
	@PostMapping
	public PollDTO initializePoll(@RequestBody PollDTO poll)
	{
		return pollService.createPoll(poll);
	}
	
	
}


