package com.example.rcv_androidapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VotingApi {

    @GET("api/poll")
    Call<List<Poll>> getPolls(); //Gets a list of all of the polls in the database

    @POST("api/poll")
    Call<Poll> createPoll(@Body Poll poll); //Sends a poll to the Spring REST which will place it in the database for us
}
