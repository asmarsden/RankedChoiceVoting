package com.example.rcv_androidapp;

public class User {

    private String username;
    private String password; //This is not populated when logged in, only used for registering account and logging in

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
