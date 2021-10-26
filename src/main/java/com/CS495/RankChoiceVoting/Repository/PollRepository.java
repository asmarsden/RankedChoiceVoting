package com.CS495.RankChoiceVoting.Repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CS495.RankChoiceVoting.Model.Poll;

@Repository
public interface PollRepository extends MongoRepository<Poll, String> {

  //public Poll findByaskedBy(String askedBy); //temporarily commented out for now
  //public List<Poll> findBypollQuestion(String pollQuestion); //temporarily commented out for now
  public Poll findByPollId(String pollId);
  public Poll findByUrlCode(String urlCode);
  public boolean existsByUrlCode(String urlCode);
  public void deleteByUrlCode(String urlCode);
  //can also implement a existsByPoll_id and deeteByPoll_id but we will do that later

}
