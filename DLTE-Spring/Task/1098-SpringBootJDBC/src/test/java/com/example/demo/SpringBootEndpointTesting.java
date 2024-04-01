package com.example.demo;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    void testApproval() throws Exception {
        String request = "{\n" +
                "    \"transactionId\": 345272L,\n" +
                "    \"transactionDate\": 2024-03-02,\n" +
                "    \"transactionBy\": \"Medhini\",\n" +
                "    \"transactionTo\": Meghana,\n" +
                "    \"transactionAmount\": 2300,\n" +
                "    \"transactionRemarks\": Family,\n" +
                "}";

        Transaction transaction = new Transaction(345272L, Date.valueOf("2024-03-02"), "Medhini", "Meghana", 2300, "Family");
        when(transactionServices.apiSave(any())).thenReturn(transaction);

        mockMvc.perform(post("/transaction").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isOk());
    }
    private SpringBootEndpointTesting andExpect(RequestMatcher value) {
        return null;
    }
    //@Test
    void testFetchById() throws Exception {
        Transaction transaction1 = new Transaction(113342L, Date.valueOf("2024-01-22"), "Medhini", "Madan", 5500, "Family");
        when(transactionServices.apiFindBySender(eq("Medhini"))).thenReturn(Optional.of(transaction1));
        mockMvc.perform((RequestBuilder) get("/transaction/113342L")).
                andExpect(status().isOk()).
                andExpect((ResultMatcher) jsonPath("$.transactionId").value(113342L)).
                andExpect((ResultMatcher) jsonPath("$.transactionDate").value("2024-01-22")).
                andExpect((ResultMatcher) jsonPath("$.transactionBy").value("Medhini")).
                andExpect((ResultMatcher) jsonPath("$.transactionTo").value("Madan")).
                andExpect((ResultMatcher) jsonPath("$.transactionAmount").value(5500)).
                andExpect((ResultMatcher) jsonPath("$.transactionRemarks").value("Family"));
    }

    //@Test
    void testFetchAllEndpoint() throws Exception {
        Transaction transaction1 = new Transaction(345272L, Date.valueOf("2024-03-02"), "Medhini", "Meghana", 2300, "Family");
        Transaction transaction2 = new Transaction(113342L, Date.valueOf("2024-01-22"), "Medhini", "Madan", 5500, "Family");
        Transaction transaction3 = new Transaction(124563L, Date.valueOf("2024-02-13"), "Medhini", "College", 2500, "Education");


        List<Transaction> expectedList = Stream.of(transaction1, transaction2, transaction3).collect(Collectors.toList());

        when(transactionServices.apiFindByReceiver(eq("Meghana"))).thenReturn(expectedList);

        mockMvc.perform((RequestBuilder) get("/transaction/view")).
                andExpect(status().isOk()).
                andExpect((ResultMatcher) jsonPath("$.transactionId").value(345272L)).
                andExpect((ResultMatcher) jsonPath("$.transactionDate").value("2024-03-02")).
                andExpect((ResultMatcher) jsonPath("$.transactionBy").value("Medhini")).
                andExpect((ResultMatcher) jsonPath("$.transactionTo").value("Meghana")).
                andExpect((ResultMatcher) jsonPath("$.transactionAmount").value(2300)).
                andExpect((ResultMatcher) jsonPath("$.transactionRemarks").value("Family"));
                andExpect(jsonPath("$[1].transactionId").value(transaction3.getTransactionId())).
                andExpect(jsonPath("$[1].transactionDate").value(transaction3.getTransactionDate())).
                andExpect(jsonPath("$[1].transactionBy").value("Medhini")).
                andExpect(jsonPath("$[1].transactionTo").value(transaction3.getTransactionTo())).
                andExpect(jsonPath("$[1].transactionAmount").value(transaction3.getTransactionAmount())).
                andExpect(jsonPath("$[1].transactionRemarks").value(transaction3.getTransactionRemarks()));


    }

    }

