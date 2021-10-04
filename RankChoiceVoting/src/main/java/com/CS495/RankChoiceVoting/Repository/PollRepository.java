package com.CS495.RankChoiceVoting.Repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.CS495.RankChoiceVoting.Model.Poll;

public interface PollRepository extends MongoRepository<Poll, String> {

  public Poll findByaskedBy(String askedBy);
  public List<Poll> findBypollQuestion(String pollQuestion);

}