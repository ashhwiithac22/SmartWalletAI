package com.example.smartwallet.models;

import java.util.Date;

public class Transaction {
    private String id;
    private String description;
    private double amount;
    private String merchant;
    private Date date;
    private String type; // debit/credit
    private String category; // AI-categorized

    public Transaction() {}

    public Transaction(String id, String description, double amount, String merchant, Date date, String type) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.merchant = merchant;
        this.date = date;
        this.type = type;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getMerchant() { return merchant; }
    public void setMerchant(String merchant) { this.merchant = merchant; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}