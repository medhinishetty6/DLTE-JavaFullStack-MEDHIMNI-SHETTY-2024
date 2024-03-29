package org.example.web;

import org.example.middleware.DatabaseTarget;
import org.example.remote.StorageTarget;
import org.omg.IOP.TransactionService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TransactionSoap {
    public TransactionService services;
    public TransactionSoap() {
        StorageTarget storageTarget = new DatabaseTarget();
        services = new TransactionService() {
        };Services(storageTarget);
    }

    private void Services(StorageTarget storageTarget) {

    }

    @WebMethod
    @WebResult(name="transaction")
    public void callFindAllTransaction(@WebParam(name = "User")Transaction transaction) {
        callFindAllTransaction(transaction);
    }
    private List<Transaction> callFindAllTransaction() {

        return null;
    }

    @WebMethod
    @WebResult(name = "Username")
    public  Transaction callFindByUsername(@WebParam(name = "String")String username) {
        return callFindByUsername(username);
    }
    @WebMethod
    @WebResult(name = "User")
    public  Transaction callfindAllByDateAndUsername(@WebParam(name = "String")String username) {
        return callFindByUsername(username);
    }


    @WebMethod
    @WebResult(name = "GroupOfTransaction")
    public GroupOfTransaction readAllByUsername(@WebParam(name = "String")String username) {
        GroupOfTransaction groupOfTransactions = new GroupOfTransaction();
        List<Transaction> transactionList = (List<Transaction>) callFindByUsername(username);
        groupOfTransactions.setTransaction(transactionList);
        return groupOfTransactions;
    }

    @WebMethod
    @WebResult(name = "GroupOfTransaction")
    public GroupOfTransaction readAllTransactions() {
        GroupOfTransaction groupOfTransactions = new GroupOfTransaction();
        List<Transaction> transactionList = callFindAllTransaction();
        groupOfTransactions.setTransaction(transactionList);
        return groupOfTransactions;
    }



}