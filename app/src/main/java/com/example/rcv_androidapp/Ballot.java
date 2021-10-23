package com.example.rcv_androidapp;

public class Ballot {

    private int id;
    private String name;
    private int[] ranking;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int[] getRanking() {
        return ranking;
    }
    public void setRanking(int[] ranking) {
        this.ranking = ranking;
    }
}
