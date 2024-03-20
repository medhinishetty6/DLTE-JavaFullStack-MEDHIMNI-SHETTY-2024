package com.example.demo;

public class Loan {
    private int loanNumber;
    private String borrowerName;
    private double loanAmount;

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", borrowerName='" + borrowerName + '\'' +
                ", loanAmount=" + loanAmount +
                '}';
    }

    public Loan(int loanNumber, String borrowerName, double loanAmount) {
        this.loanNumber = loanNumber;
        this.borrowerName = borrowerName;
        this.loanAmount = loanAmount;
    }

    public int getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
}
