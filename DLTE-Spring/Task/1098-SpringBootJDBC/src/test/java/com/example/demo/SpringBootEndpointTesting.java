package com.example.demo;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SpringBootEndpointTesting {
    @MockBean
    private TransactionServices transactionServices;
    @InjectMocks
    private TransactionController transactionController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    private void testNewTransaction() throws Exception {
        Transaction transaction = new Transaction(234598L, new Date(2024, 03, 19), "Medhini", "Meghana", 900, "Family");
        when(transactionServices.apiSave(any(Transaction.class))).thenReturn(transaction);
        mockMvc.perform(MockMvcRequestBuilders.post("/transactions/add").contentType(MediaType.APPLICATION_JSON).content("{\"transactionId\":234598,\"transactionDate\":\"2024-03-19T00:00:00\",\"transactionTo\":\"Meghana\",\"transactionBy\":\"Medhini\",\"transactionAmount\":900,\"transactionRemarks\":\"Family\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFindBySender() throws Exception {
        Transaction transaction = new Transaction(234598L, new Date(2024, 03, 19), "Medhini", "Meghana", 900, "Family");
        List<Transaction> transactions = Arrays.asList(transaction);
        when(transactionServices.apiFindBySender("Medhini")).thenReturn(transactions);
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/sender/Medhini")
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                // .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionBy").value("Meghana"));//fail
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionBy").value("Medhini"));//Success
    }

    @Test
    public void testFindByReceiver() throws Exception {
        Transaction transaction = new Transaction(234598L, new Date(2024, 03, 19), "Medhini", "Meghana", 900, "Family");
        List<Transaction> transactions = Arrays.asList(transaction);
        when(transactionServices.apiFindByReceiver("Meghana")).thenReturn(transactions);
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/receiver/Meghana")
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                // .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionBy").value("Medhini"));//fail
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionTo").value("Meghana"));//Success
    }

    @Test
    public void testFindByamount() throws Exception {
        Transaction transaction = new Transaction(234598L, new Date(2024, 03, 19), "Medhini", "Meghana", 900, "Family");
        List<Transaction> transactions = Arrays.asList(transaction);
        when(transactionServices.apiFindByAmount(900)).thenReturn(transactions);
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/amount/900")
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                // .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionAmount").value(200));//fail
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionAmount").value(900));//Success
    }
  
}

