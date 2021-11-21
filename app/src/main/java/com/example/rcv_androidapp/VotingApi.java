package com.example.rcv_androidapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VotingApi {

    @GET("api/poll/{urlCode}")
    Call<Poll> getPoll(@Path("urlCode") String urlCode);

    @POST("api/poll")
    Call<Poll> createPoll(@Body Poll poll); //Sends a poll to the Spring REST which will place it in the database for us

    @POST("api/poll/{urlCode}/vote")
    Call<Ballot> castBallot(@Body Ballot ballot); //casts a ballot to the appropriate poll
}
