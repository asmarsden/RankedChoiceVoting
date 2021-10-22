package com.CS495.RankChoiceVoting.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CS495.RankChoiceVoting.Repository.PollRepository;
import com.CS495.RankChoiceVoting.Repository.VoteRepository;
import com.CS495.RankChoiceVoting.Model.Vote;
import com.CS495.RankChoiceVoting.Model.Poll;


@Service
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private PollRepository pollRepository;
	
	@Override
	public void vote (String vID)
	{
		Optional<Vote> optVote = voteRepository.findByVoteCode(vID);
		Vote vote = optVote.get();
		//Poll poll = pollRepository.findByVotesList(vote);
		
		vote.setVoteCount(vote.getVoteCount() + 1 );
		voteRepository.save(vote);
	}
	
	@Override
	public void putAllVotes(List<String> voteCodes /*, String ip */)
	{
		 if ( voteCodes != null && !voteCodes.isEmpty() )
         {
                 //Poll poll = pollRepository.findByVotesList( voteRepository.findByVoteCode( voteCodes.get( 0 ) ).get() );
                //if check for user having voted already when multiplevotes is disabled {}
                 
                         

                 voteCodes.stream() //filter by each voteCode in list, find it, get it, update voteCount and save the vote
                         .map( voteRepository::findByVoteCode )
                         .map( Optional::get )
                         .forEach( e -> {
                                 e.setVoteCount( e.getVoteCount() + 1 );
                                 voteRepository.save( e );
                         } );
         }
	}
}