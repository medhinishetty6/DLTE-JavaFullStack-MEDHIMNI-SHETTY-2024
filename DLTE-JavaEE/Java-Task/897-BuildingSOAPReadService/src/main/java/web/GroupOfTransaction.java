package web;

import org.example.entity.Transaction;

import java.util.List;

public class GroupOfTransaction {

    private List<Transaction> transactionList;
    public List<Transaction> getTransactionList(){
        return transactionList;
    }
    public void setTransactionList(List<Transaction> transactionList){
        this.transactionList=transactionList;
    }
    @Override
    public String toString(){
        return "GroupOfTransaction{"+"transaction=" + transactionList + '}';
    }
}
