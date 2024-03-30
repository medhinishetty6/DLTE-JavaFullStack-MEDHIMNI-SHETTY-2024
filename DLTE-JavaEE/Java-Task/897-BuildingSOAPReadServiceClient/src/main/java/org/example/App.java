package org.example;

import org.omg.IOP.TransactionService;
import web.Transaction;

import java.util.List;


public class App {
    public static Transaction main(String[] args ) {
        TransactionService transactionService=new TransactionService() {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        Transaction transaction=new Transaction();
        String transaction1=transaction.getReceiver();
        List<Transaction> transactions=(List<Transaction>) transaction.findAllByType("Open");
        for (Transaction each:transactions){
            return each;
        }
        return transaction;
    }
}
