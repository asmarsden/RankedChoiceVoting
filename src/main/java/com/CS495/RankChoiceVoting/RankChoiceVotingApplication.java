package com.CS495.RankChoiceVoting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import com.CS495.RankChoiceVoting.Model.Customer;
import com.CS495.RankChoiceVoting.Model.Poll;
//import com.CS495.RankChoiceVoting.Repository.CustomerRepository;
import com.CS495.RankChoiceVoting.Repository.PollRepository;

@SpringBootApplication
@EnableMongoRepositories
@ComponentScan
public class RankChoiceVotingApplication {
	
	  public static void main(String[] args) {
		    SpringApplication.run(RankChoiceVotingApplication.class, args);
		  }

	
	  @Bean
	  public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	      @Override
	      public void addCorsMappings(CorsRegistry registry) {
	       registry.addMapping("/**").allowedOrigins("*")
	                      .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE");
	      }
	    };
	  }
}
