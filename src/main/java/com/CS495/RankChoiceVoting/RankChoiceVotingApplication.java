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
public class RankChoiceVotingApplication implements CommandLineRunner {

  @Autowired
  private PollRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(RankChoiceVotingApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    //repository.deleteAll();

    // save a couple of polls
    //repository.save(new Poll("How many sprints will this project take?", "Griffin"));
    //repository.save(new Poll("Who will win the Iron Bowl this year?", "A hopeful Auburn Fan"));
    //repository.save(new Poll("What is the chance that I graduate on time?", "Concerned Student"));
    //repository.save(new Poll("Why is Dr. Monica Anderson the best 495 teacher?", "Bailey Hemphill"));

    // fetch all customers
    System.out.println("Polls found with findAll():");
    System.out.println("-------------------------------");
    //for (Poll poll : repository.findAll()) {
    //  System.out.println(poll);
    //}
   // System.out.println();

    // fetch a poll meeting conditions
    System.out.println("Poll found with findByaskedBy('Griffin'):");
    System.out.println("--------------------------------");
  //  System.out.println(repository.findByaskedBy("Griffin"));
    System.out.println(" ");

    System.out.println("Polls found with findBypollQuestion('Who will win the Iron Bowl this year?'):");
    System.out.println("--------------------------------");
    //for (Poll poll : repository.findBypollQuestion("Who will win the Iron Bowl this year?")) {
     // System.out.println(poll);
   // }

  }

}
