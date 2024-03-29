package org.example.web;

import java.util.List;

public class GroupOfTransaction {
    private List<Transaction> transaction;

    @Override
    public String toString() {
        return "GroupOfTransaction{" +
                "transaction=" + transaction +
                '}';
    }

    public GroupOfTransaction() {
    }

    public GroupOfTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
}
