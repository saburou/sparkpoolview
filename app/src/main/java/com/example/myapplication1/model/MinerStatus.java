package com.example.myapplication1.model;

public class MinerStatus {
    private String name;
    private double hashrate24H;
    private int validShares;
    private int staleShares;
    private int invalidShares;

    public MinerStatus() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHashrate24H() {
        return hashrate24H;
    }

    public void setHashrate24H(double hashrate24H) {
        this.hashrate24H = hashrate24H;
    }

    public int getValidShares() {
        return validShares;
    }

    public void setValidShares(int validShares) {
        this.validShares = validShares;
    }

    public int getStaleShares() {
        return staleShares;
    }

    public void setStaleShares(int staleShares) {
        this.staleShares = staleShares;
    }

    public int getInvalidShares() {
        return invalidShares;
    }

    public void setInvalidShares(int invalidShares) {
        this.invalidShares = invalidShares;
    }

    public double getValidPercentage() {
        return calcPercentage(validShares, validShares + staleShares + invalidShares);
    }

    public double getStalePercentage() {
        return calcPercentage(staleShares, validShares + staleShares + invalidShares);
    }

    public double getInvalidPercentage() {
        return calcPercentage(invalidShares, validShares + staleShares + invalidShares);
    }

    public static double calcPercentage(int targetShares, int totalShares){
        if(totalShares == 0) return 0;
        return (double)targetShares / (double)totalShares;
    }
}
