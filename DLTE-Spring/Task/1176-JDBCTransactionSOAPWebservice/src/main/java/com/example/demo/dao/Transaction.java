package com.example.demo.dao;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private Date transactionDate;
    private String transactionTo;
    private Double transactionAmount;
    private String transactionRemarks;
    private String  transactionBy;

    public Transaction() {
    }

    public Transaction(int transactionId, Date transactionDate, String transactionTo, Double transactionAmount, String transactionRemarks, String transactionBy) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionTo = transactionTo;
        this.transactionAmount = transactionAmount;
        this.transactionRemarks = transactionRemarks;
        this.transactionBy = transactionBy;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionRemarks='" + transactionRemarks + '\'' +
                ", transactionBy='" + transactionBy + '\'' +
                '}';
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }
}
