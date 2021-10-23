package com.example.rcv_androidapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VotingApi {

    @GET("api/poll")
    Call<List<Poll>> getPolls(); //Gets a list of all of the polls in the database
}
