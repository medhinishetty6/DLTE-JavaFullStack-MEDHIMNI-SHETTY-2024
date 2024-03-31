package com.example.demo;

import com.example.demo.configs.SoapPhase;
import com.example.demo.dao.Transaction;
import com.example.demo.dao.TransactionService;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.convert.ClassGeneratingEntityInstantiator;
import services.transaction.AddTransactionRequest;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import services.transaction.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SoapEndPointTesting {
    @MockBean
    private TransactionService transactionService;

    @InjectMocks
    SoapPhase soapPhase;
    @Test
    void testNewTransaction(){
        Transaction transaction1=new Transaction(001,new Date(2024,01,10),"Meghana",900.00,"Family","Medhini");
        Transaction transaction2=new Transaction(002,new Date(2024,02,01),"Madan",9500.00,"Family","Medhini");
        Transaction transaction3=new Transaction(003,new Date(2024,01,22),"Megha",9900.00,"Education","Madan");
        when(transactionService.newTransaction(any())).thenReturn(transaction1);
        AddTransactionRequest addTransactionRequest=new AddTransactionRequest();
        services.transaction.Transaction transaction=new services.transaction.Transaction();
        transaction.setTransactionId(001);
        transaction.setTransactionDate(XMLGregorianCalendarImpl.createDate(2024,01,10,0));
        transaction.setTransactionTo("Meghana");
        transaction.setTransactionAmount(900.00);
        transaction.setTransactionRemarks("Family");
        transaction.setTransactionBy("Medhini");
        addTransactionRequest.setTransaction(transaction);
        AddTransactionResponse addTransactionResponse=soapPhase.addTransactionResponse(addTransactionRequest);
        assertEquals("SUCCESS",addTransactionResponse.getServiceStatus().getStatus());
        assertSame(transaction1.getTransactionId(),addTransactionResponse.getTransaction().getTransactionId());

    }

    @Test
    public void testBySender(){
        List<Transaction>  newTransaction=new ArrayList<>();
        newTransaction.add(new Transaction(001,new Date(2024,01,10),"Meghana",900.00,"Family","Medhini"));
        when(transactionService.findBySender("Medhini")).thenReturn(newTransaction);
        SenderRequest senderRequest=new SenderRequest();
        senderRequest.setSender("Medhini");
        SenderResponse senderResponse=soapPhase.senderResponse(senderRequest);
        assertEquals("SUCCESS",senderResponse.getServiceStatus().getMessage());

    }

    @Test
    public void testByReceiver(){
        List<Transaction>  newTransaction=new ArrayList<>();
        newTransaction.add(new Transaction(001,new Date(2024,01,10),"Meghana",900.00,"Family","Medhini"));
        when(transactionService.findByReceiver("Meghana")).thenReturn(newTransaction);
        ReceiverRequest receiverRequest=new ReceiverRequest();
        receiverRequest.setReceiver("Meghana");
        ReceiverResponse receiverResponse=soapPhase.receiverResponse(receiverRequest);
        assertEquals("SUCCESS",receiverResponse.getServiceStatus().getMessage());
    }

    @Test
    public void testByAmount(){
        List<Transaction>  newTransaction=new ArrayList<>();
        newTransaction.add(new Transaction(001,new Date(2024,01,10),"Meghana",900.00,"Family","Medhini"));
        when(transactionService.findByAmount(9500.00)).thenReturn(newTransaction);
        AmountRequest amountRequest=new AmountRequest();
        amountRequest.setAmount(9500.00);
        AmountResponse amountResponse=soapPhase.amountResponse(amountRequest);
        assertEquals("SUCCESS",amountResponse.getServiceStatus().getMessage());
    }
    @Test
    public void testUpdateRemarks(){
        UpdateRemarksRequest updateRemarksRequest=new UpdateRemarksRequest();
        Transaction transaction=new Transaction();
        transaction.setTransactionId(001);
        transaction.setTransactionDate(new Date(2024,01,10));
        transaction.setTransactionTo("Meghana");
        transaction.setTransactionAmount(900.00);
        transaction.setTransactionRemarks("Family");
        transaction.setTransactionBy("Medhini");
        Transaction transaction1=new Transaction(001,new Date(2024,01,10),"Meghana",900.00,"Family","Medhini");
        when(transactionService.updateRemarks(any())).thenReturn(transaction1);
        UpdateRemarksResponse updateRemarksResponse=soapPhase.updateRemarks(updateRemarksRequest);
        assertEquals("SUCCESS", updateRemarksResponse.getServiceStatus().getStatus());
        assertTrue(transaction1.getTransactionId()==updateRemarksResponse .getTransaction().getTransactionId());

    }
    @Test
    public void testRemoveTransaction(){
        Transaction transaction1=new Transaction(001,new Date(2024,01,10),"Meghana",900.00,"Family","Medhini");
        Transaction transaction2=new Transaction(002,new Date(2024,02,01),"Madan",9500.00,"Family","Medhini");
        Transaction transaction3=new Transaction(003,new Date(2024,01,22),"Megha",9900.00,"Education","Madan");
        List<Transaction> transactionsList = Stream.of(transaction1, transaction2, transaction3).collect(Collectors.toList());
        DeleteDatesRequest deleteDatesRequest=new DeleteDatesRequest();
        deleteDatesRequest.setStartDate(XMLGregorianCalendarImpl.createDate(2024,01,10,0));
        deleteDatesRequest.setStartDate(XMLGregorianCalendarImpl.createDate(2024,02,01,0));
        when(transactionService.removeTransactionsBetweenDates(any(java.util.Date.class),any(java.util.Date.class))).thenReturn("updated");
        DeleteDatesResponse deleteDatesResponse=soapPhase.datesResponse(deleteDatesRequest);
        assertEquals("updated",deleteDatesResponse.getServiceStatus().getMessage());
    }

}
