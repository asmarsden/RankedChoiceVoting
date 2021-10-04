package com.CS495.RankChoiceVoting.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Locale;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Polls")
public class Poll {

  @Id
  public String id;

  public String pollQuestion;
  public String askedBy;
 
  public String createdAt; 

  public Poll() {}

  public Poll(String pollQuestion, String askedBy) {
    this.pollQuestion = pollQuestion;
    this.askedBy = askedBy;
    this.createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }

  @Override
  public String toString() {
    return String.format(
    "Poll[id=%s, pollQuestion='%s', askedBy='%s', createdAt='%s']",
        id, pollQuestion, askedBy, createdAt);
  }

}