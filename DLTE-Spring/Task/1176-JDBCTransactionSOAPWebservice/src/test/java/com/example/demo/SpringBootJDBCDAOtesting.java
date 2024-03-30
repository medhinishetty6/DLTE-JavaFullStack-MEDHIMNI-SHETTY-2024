package com.example.demo;

import com.example.demo.configs.SoapPhase;
import com.example.demo.dao.Transaction;
import com.example.demo.dao.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import com.example.demo.dao.TransactionService.TransactionMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SpringBootJDBCDAOtesting {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TransactionService transactionService;
       private List<Transaction> beginTransaction(){
           List<Transaction> newtransactionList=new ArrayList<>();
           Transaction transaction1=new Transaction(001,new Date(11/01/2024),"Meghana",900.00,"Family","Medhini");
           Transaction transaction2=new Transaction(002,new Date(01/02/2024),"Madan",9500.00,"Family","Medhini");
           Transaction transaction3=new Transaction(003,new Date(22/01/2024),"Megha",9900.00,"Education","Madan");
           newtransactionList.add(transaction1);
           newtransactionList.add(transaction2);
           newtransactionList.add(transaction3);
           return newtransactionList;
       }
    @Test
    void testNewTransaction(){
        Transaction transaction1=new Transaction(001,new Date(11/01/2024),"Meghana",900.00,"Family","Medhini");
//        Transaction transaction2=new Transaction(002,new Date(01/02/2024),"Madan",9500.00,"Family","Medhini");
//        Transaction transaction3=new Transaction(003,new Date(22/01/2024),"Megha",9900.00,"Education","Madan");
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
        Transaction result = transactionService.newTransaction(transaction1);
        assertEquals(transaction1, result);
    }

    @Test
    void testfindBySender(){
        Transaction transaction1=new Transaction(001,new Date(11/01/2024),"Meghana",900.00,"Family","Medhini");
        Transaction transaction2=new Transaction(002,new Date(01/02/2024),"Madan",9500.00,"Family","Medhini");
        Transaction transaction3=new Transaction(003,new Date(22/01/2024),"Megha",9900.00,"Education","Madan");
        List<Transaction> transactionList= Stream.of(transaction1,transaction2,transaction3).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(transactionList);
        List<Transaction> transactionResult1=transactionService.findBySender("Madan");
        assertNotNull(transactionResult1);
        assertEquals(transactionList,transactionResult1);
    }
    @Test
    void testfindByReciever(){
        Transaction transaction1=new Transaction(001,new Date(11/01/2024),"Meghana",900.00,"Family","Medhini");
        Transaction transaction2=new Transaction(002,new Date(01/02/2024),"Madan",9500.00,"Family","Medhini");
        Transaction transaction3=new Transaction(003,new Date(22/01/2024),"Megha",9900.00,"Education","Madan");
        List<Transaction> transactionList= Stream.of(transaction1,transaction2,transaction3).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(transactionList);
        List<Transaction> transactionResult1=transactionService.findByReceiver("Meghana");
        assertNotNull(transactionResult1);
        assertEquals(transactionList,transactionResult1);
    }

    @Test
    void testfindByAmount(){
        double amount = 900.00;
        List<Transaction> transactionList = beginTransaction();
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class))).thenReturn(transactionList);
        List<Transaction> transactionResult2 = transactionService.findByAmount(amount);
        assertEquals(1, transactionResult2.size());
    }

    @Test
    void testUpdateRemarks(){
           List<Transaction>  updateTransaction=beginTransaction();
           when(jdbcTemplate.query(any(String.class),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(updateTransaction);
           Transaction transactionResult1=transactionService.updateRemarks(updateTransaction.get(0));
           assertEquals(updateTransaction,transactionResult1);

    }

    @Test
    void testRemoveTransactionBetweenDates(){
           Date startDate=new Date(01/01/2024);
           Date endDate=new Date(10/01/2024);
        when(jdbcTemplate.update(any(String.class), any(), any())).thenReturn(1);
        String transactionResult1=transactionService.removeTransactionsBetweenDates(startDate,endDate);
        assertEquals("updated",transactionResult1);

    }

}
