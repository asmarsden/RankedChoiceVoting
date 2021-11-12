package com.CS495.RankChoiceVoting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//import com.CS495.RankChoiceVoting.Model.Customer;
import com.CS495.RankChoiceVoting.Model.Poll;
//import com.CS495.RankChoiceVoting.Repository.CustomerRepository;
import com.CS495.RankChoiceVoting.Repository.PollRepository;

@SpringBootApplication
@EnableMongoRepositories
@ComponentScan
public class RankChoiceVotingApplication {

//  @Autowired
//  private PollRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(RankChoiceVotingApplication.class, args);
  }

}
