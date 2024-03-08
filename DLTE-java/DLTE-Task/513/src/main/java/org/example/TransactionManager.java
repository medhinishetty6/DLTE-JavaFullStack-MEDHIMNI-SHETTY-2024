package org.example;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class TransactionManager {

    ArrayList<Transaction> transactions;

    public TransactionManager(){
        transactions=new ArrayList<>();
    }
    public void insertTransaction(Date dateoftransaction, Double transactionamount, String to, String remarks){
         transactions.add(new Transaction(dateoftransaction,transactionamount,to,remarks));
    }

    public void deletTransaction(String to){
        Iterator<Transaction> iterator=transactions.iterator();
        while ((iterator.hasNext())){
            Transaction transaction=iterator.next();
            if(transaction.getTo().equals(transaction)){
                iterator.remove();
            }
        }
    }
    public ArrayList<Transaction> filterTransactionsforEducation(){
        ArrayList<Transaction> educationTransaction= new ArrayList<>();
        for(Transaction transaction : transactions){
            if(transaction.remarks.equals("Education")){
                educationTransaction.add(transaction);
            }
        }
        return educationTransaction;
    }
}
