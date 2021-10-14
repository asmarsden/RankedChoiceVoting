package com.CS495.RankChoiceVoting.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CS495.RankChoiceVoting.Model.Tutorial;

public interface TutorialRepository extends MongoRepository<Tutorial, String> {
  List<Tutorial> findByTitleContaining(String title);
  List<Tutorial> findByPublished(boolean published);
}
