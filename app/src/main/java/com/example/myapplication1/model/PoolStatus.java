package com.example.myapplication1.model;

public class PoolStatus {
    private Currency currency;
    private double hashrate;
    private double networkHashrate;
    private double income24H;
    private double difficulty;
    private double incomeInUSD;

    public PoolStatus() {
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getHashrate() {
        return hashrate;
    }

    public void setHashrate(double hashrate) {
        this.hashrate = hashrate;
    }

    public double getNetworkHashrate() {
        return networkHashrate;
    }

    public void setNetworkHashrate(double networkHashrate) {
        this.networkHashrate = networkHashrate;
    }

    public double getIncome24H() {
        return income24H;
    }

    public void setIncome24H(double income24H) {
        this.income24H = income24H;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public double getIncomeInUSD() {
        return incomeInUSD;
    }

    public void setIncomeInUSD(double incomeInUSD) {
        this.incomeInUSD = incomeInUSD;
    }
}
