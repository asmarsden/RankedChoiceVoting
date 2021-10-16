package com.CS495.RankChoiceVoting.Repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CS495.RankChoiceVoting.Model.Poll;

@Repository
public interface PollRepository extends MongoRepository<Poll, String> {

  public Poll findByaskedBy(String askedBy);
  public List<Poll> findBypollQuestion(String pollQuestion);
  public Poll findByPollID(String PollID);
  
  

}