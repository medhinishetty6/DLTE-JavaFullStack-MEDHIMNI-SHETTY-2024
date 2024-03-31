package com.example.demo;

import com.example.demo.configs.SoapPhase;
import com.example.demo.dao.Transaction;
import com.example.demo.dao.TransactionService;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.transaction.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SoapEndPointTesting {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    SoapPhase soapPhase;

    @Test
    void testNewTransaction() {
        // Mocking TransactionService behavior
        when(transactionService.newTransaction(any(Transaction.class))).thenReturn(new Transaction());

        // Creating a sample request
        AddTransactionRequest addTransactionRequest = new AddTransactionRequest();
        services.transaction.Transaction transaction = new services.transaction.Transaction();
        transaction.setTransactionId(1);
        transaction.setTransactionDate(XMLGregorianCalendarImpl.createDate(2024, 1, 10, 0));
        transaction.setTransactionTo("Meghana");
        transaction.setTransactionAmount(900.00);
        transaction.setTransactionRemarks("Family");
        transaction.setTransactionBy("Medhini");
        addTransactionRequest.setTransaction(transaction);

        // Invoking SOAP endpoint
        AddTransactionResponse addTransactionResponse = soapPhase.addTransactionResponse(addTransactionRequest);

        // Verifying response
        assertEquals("SUCCESS", addTransactionResponse.getServiceStatus().getStatus());
    }

    @Test
    public void testBySender() {
        // Mocking TransactionService behavior
        List<Transaction> newTransaction = new ArrayList<>();
        newTransaction.add(new Transaction());
        when(transactionService.findBySender("Medhini")).thenReturn(newTransaction);

        // Creating a sample request
        SenderRequest senderRequest = new SenderRequest();
        senderRequest.setSender("Medhini");

        // Invoking SOAP endpoint
        SenderResponse senderResponse = soapPhase.senderResponse(senderRequest);

        // Verifying response
        assertEquals("SUCCESS", senderResponse.getServiceStatus().getMessage());
    }

    @Test
    public void testByReceiver() {
        // Mocking TransactionService behavior
        List<Transaction> newTransaction = new ArrayList<>();
        newTransaction.add(new Transaction());
        when(transactionService.findByReceiver("Meghana")).thenReturn(newTransaction);

        // Creating a sample request
        ReceiverRequest receiverRequest = new ReceiverRequest();
        receiverRequest.setReceiver("Meghana");

        // Invoking SOAP endpoint
        ReceiverResponse receiverResponse = soapPhase.receiverResponse(receiverRequest);

        // Verifying response
        assertEquals("SUCCESS", receiverResponse.getServiceStatus().getMessage());
    }

    @Test
    public void testByAmount() {
        // Mocking TransactionService behavior
        List<Transaction> newTransaction = new ArrayList<>();
        newTransaction.add(new Transaction());
        when(transactionService.findByAmount(9500.00)).thenReturn(newTransaction);

        // Creating a sample request
        AmountRequest amountRequest = new AmountRequest();
        amountRequest.setAmount(9500.00);

        // Invoking SOAP endpoint
        AmountResponse amountResponse = soapPhase.amountResponse(amountRequest);

        // Verifying response
        assertEquals("SUCCESS", amountResponse.getServiceStatus().getMessage());
    }

    @Test
    public void testUpdateRemarks(){
        // Creating a sample request
        UpdateRemarksRequest updateRemarksRequest=new UpdateRemarksRequest();

        Transaction transaction=new Transaction();
        transaction.setTransactionId(001);
        transaction.setTransactionDate(new Date(2024,01,10));
        transaction.setTransactionTo("Meghana");
        transaction.setTransactionAmount(900.00);
        transaction.setTransactionRemarks("Family");
        transaction.setTransactionBy("Medhini");
        Transaction transaction1=new Transaction(001,new Date(2024,01,10),"Meghana",900.00,"Family","Medhini");

        // Mocking TransactionService behavior
        when(transactionService.updateRemarks(any())).thenReturn(transaction1);

        // Invoking SOAP endpoint
        UpdateRemarksResponse updateRemarksResponse=soapPhase.updateRemarks(updateRemarksRequest);

        // Verifying response
        assertEquals("SUCCESS", updateRemarksResponse.getServiceStatus().getStatus());
        assertTrue(transaction1.getTransactionId()==updateRemarksResponse .getTransaction().getTransactionId());

    }

    @Test
    public void testRemoveTransaction() {
        // Mocking TransactionService behavior
        when(transactionService.removeTransactionsBetweenDates(any(Date.class), any(Date.class))).thenReturn("updated");

        // Creating a sample request
        DeleteDatesRequest deleteDatesRequest = new DeleteDatesRequest();
        deleteDatesRequest.setStartDate(XMLGregorianCalendarImpl.createDate(2024, 1, 10, 0));
        deleteDatesRequest.setEndDate(XMLGregorianCalendarImpl.createDate(2024, 2, 1, 0));

        // Invoking SOAP endpoint
        DeleteDatesResponse deleteDatesResponse = soapPhase.datesResponse(deleteDatesRequest);

        // Verifying response
        assertEquals("updated", deleteDatesResponse.getServiceStatus().getMessage());
    }
}

