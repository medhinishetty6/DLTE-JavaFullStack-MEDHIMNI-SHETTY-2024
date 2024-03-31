package com.example.demo.configs;

import com.example.demo.dao.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.transaction.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class SoapPhase {
    private final String url = "http://transaction.services";
    @Autowired
    private TransactionService transactionService;

    @PreAuthorize("hasAuthority('admin')")
    @PayloadRoot(namespace = url, localPart = "addTransactionRequest")
    @ResponsePayload
    public AddTransactionResponse addTransactionResponse(@RequestPayload AddTransactionRequest addTransactionRequest) {
        AddTransactionResponse addTransactionResponse = new AddTransactionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        services.transaction.Transaction transaction1 = addTransactionRequest.getTransaction();
        Date date=addTransactionRequest.getTransaction().getTransactionDate().toGregorianCalendar().getTime();
        com.example.demo.dao.Transaction daotransaction = new com.example.demo.dao.Transaction();
        daotransaction.setTransactionDate(date);
        BeanUtils.copyProperties(transaction1,daotransaction);
        daotransaction = transactionService.newTransaction(daotransaction);
        if (daotransaction != null) {
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(transaction1, daotransaction);
            addTransactionResponse.setTransaction(transaction1);
            serviceStatus.setMessage("Transaction added successfully");
        } else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage("Failed to add transaction");
        }
        return addTransactionResponse;
    }

    @PreAuthorize("hasAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "amountRequest")
    @ResponsePayload
    public AmountResponse amountResponse(@RequestPayload AmountRequest amountRequest) {
        AmountResponse amountResponse = new AmountResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> transactionList = new ArrayList<>();
        List<com.example.demo.dao.Transaction> transaction = transactionService.findByAmount(amountRequest.getAmount());


        Iterator<com.example.demo.dao.Transaction> iterator = transaction.iterator();
        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransactions = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransactions);
            transactionList.add(currentTransactions);
        }
        if (transaction != null && !transaction.isEmpty()) {
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage("transactions found for the amount");
            amountResponse.getTransaction().addAll(transactionList);
        } else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage("transaction not found for the amount:" + amountRequest.getAmount());
        }
        amountResponse.setServiceStatus(serviceStatus);
        return amountResponse;
    }

    @PreAuthorize("hasAuthority('admin')")
    @PayloadRoot(namespace = url, localPart = "deleteDatesRequest")
    @ResponsePayload
    public DeleteDatesResponse datesResponse(@RequestPayload DeleteDatesRequest deleteDatesRequest) {
        DeleteDatesResponse deleteDatesResponse=new DeleteDatesResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        Date startDate=deleteDatesRequest.getStartDate().toGregorianCalendar().getTime();
        Date endDate=deleteDatesRequest.getEndDate().toGregorianCalendar().getTime();
       String message = transactionService.removeTransactionsBetweenDates(startDate,endDate);
        Date StartDate=deleteDatesRequest.getStartDate().toGregorianCalendar().getTime();
        Date EndDate=deleteDatesRequest.getEndDate().toGregorianCalendar().getTime();
        if(message!=null){
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage(message);
        }else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage("Failed to remove transactions between dates");
        }
        deleteDatesResponse.setServiceStatus(serviceStatus);
        return deleteDatesResponse;

    }

    @PreAuthorize("hasAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "receiverRequest")
    @ResponsePayload
    public ReceiverResponse receiverResponse(@RequestPayload ReceiverRequest request) {
        ReceiverResponse response = new ReceiverResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> transactionsList = new ArrayList<>();
        List<com.example.demo.dao.Transaction> transaction = transactionService.findByReceiver(request.getReceiver());

        Iterator<com.example.demo.dao.Transaction> iterator = transaction.iterator();
        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransactions = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransactions);
            transactionsList.add(currentTransactions);
        }
        if (transaction != null && !transaction.isEmpty()) {
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage("transactions found for the receiver");
            response.getTransaction().addAll(transactionsList);
        } else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage("No transactions found for receiver: " + request.getReceiver());
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PreAuthorize("hasAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "senderRequest")
    @ResponsePayload
    public SenderResponse senderResponse(@RequestPayload SenderRequest request) {
        SenderResponse response = new SenderResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> transactionsList = new ArrayList<>();
        List<com.example.demo.dao.Transaction> transaction = transactionService.findBySender(request.getSender());

        Iterator<com.example.demo.dao.Transaction> iterator = transaction.iterator();
        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransactions = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransactions);
            transactionsList.add(currentTransactions);
        }
        if (transaction != null && !transaction.isEmpty()) {
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage("transactions found for the sender");
            response.getTransaction().addAll(transactionsList);
        } else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage("No transactions found for sender: " + request.getSender());
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('admin','manager')")
    @PayloadRoot(namespace = url, localPart = "updateRemarksRequest")
    @ResponsePayload
    public UpdateRemarksResponse updateRemarks(@RequestPayload UpdateRemarksRequest request) {
        UpdateRemarksResponse response = new UpdateRemarksResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        services.transaction.Transaction transaction=new services.transaction.Transaction();
        com.example.demo.dao.Transaction message=new  com.example.demo.dao.Transaction();
        BeanUtils.copyProperties(request.getTransaction(),message);
        message = transactionService.updateRemarks(message);
        if(message!=null){
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(message,transaction);
            serviceStatus.setMessage("Updated remarks for transaction:"+message.getTransactionId());
        }else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage("Failed to update remarks for transaction: " + message.getTransactionId());
        }
         response.setTransaction(transaction);
         response.setServiceStatus(serviceStatus);
         return response;
    }




}
