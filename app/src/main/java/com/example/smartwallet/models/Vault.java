package com.example.smartwallet.models;

public class Vault {
    private String id;
    private String name;
    private double balance;
    private double target;

    public Vault() {}

    public Vault(String id, String name, double balance, double target) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.target = target;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public double getTarget() { return target; }
    public void setTarget(double target) { this.target = target; }
}