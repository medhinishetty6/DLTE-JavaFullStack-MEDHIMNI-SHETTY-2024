package com.example.demo;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SpringBootJDBCTesting {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TransactionServices transactionServices;

    @Test
    void testSpringContextLoads() {
        assertNotNull(transactionServices, "Spring context not loaded ");
    }



    @Test
    void testNewTransaction() {
        Transaction transaction1 = new Transaction(345272L, Date.valueOf("2024-01-20"), "Medhini", "Meghana", 50000, "Family");
        Transaction transaction2=new Transaction(113342L, Date.valueOf("2024-03-11"),"Medhini","Madan",2400,"Family");
        when(jdbcTemplate.update(
                eq("insert into transactions_table values(?,?,?,?,?,?)"),
                any(Long.class),
                any(Date.class),
                any(String.class),
                any(String.class),
                any(Integer.class),
                any(String.class)
        )).thenReturn(1);
        Transaction transactionActual = transactionServices.apiSave(transaction1);
        System.out.println(transactionActual.getTransactionBy());
        assertEquals(transaction1.getTransactionBy(),transactionActual.getTransactionBy());
        assertEquals(transaction2.getTransactionBy(),transactionActual.getTransactionBy());//fails


    }

    @Test
    void testFindBySender(){
        Transaction transaction1=new Transaction(345272L,Date.valueOf("2024-03-02"),"Medhini","Meghana",2300,"Family");
        Transaction transaction2=new Transaction(113342L, Date.valueOf("2024-01-22"),"Medhini","Madan",5500,"Family");
        Transaction transaction3=new Transaction(124563L,Date.valueOf("2024-02-13"),"Medhini","College",2500,"Education");
        List<Transaction> expected= Stream.of(transaction1,transaction2,transaction3).collect(Collectors.toList());
        List<Transaction> notExpected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transaction> actual=transactionServices.apiFindBySender("Divija");
        assertEquals(expected,actual);
        assertNotEquals(notExpected,actual);
        assertNull(expected);
    }

    @Test
    void testFindByReceiver(){
        Transaction transaction1=new Transaction(345272L,Date.valueOf("2024-03-02"),"Medhini","Meghana",2300,"Family");
        Transaction transaction2=new Transaction(113342L, Date.valueOf("2024-01-22"),"Medhini","Madan",5500,"Family");
        Transaction transaction3=new Transaction(124563L,Date.valueOf("2024-02-13"),"Medhini","College",2500,"Education");
        List<Transaction> expected= Stream.of(transaction1).collect(Collectors.toList());
        List<Transaction> notExpected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transaction> actual=transactionServices.apiFindByReceiver("Spandana");
        assertEquals(expected,actual);
        assertNotEquals(notExpected,actual);
        assertNotNull(expected);
        assertNotNull(actual);
    }

    @Test
    void testFindByAmount(){
        Transaction transaction1=new Transaction(345272L,Date.valueOf("2024-03-02"),"Medhini","Meghana",2300,"Family");
        Transaction transaction2=new Transaction(113342L, Date.valueOf("2024-01-22"),"Medhini","Madan",5500,"Family");
        Transaction transaction3=new Transaction(124563L,Date.valueOf("2024-02-13"),"Medhini","College",2500,"Education");
        List<Transaction> expected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        List<Transaction> notExpected= Stream.of(transaction1).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transaction> actual=transactionServices.apiFindByAmount(400);
        assertEquals(expected,actual);
        assertNotEquals(notExpected,actual);
    }
}
