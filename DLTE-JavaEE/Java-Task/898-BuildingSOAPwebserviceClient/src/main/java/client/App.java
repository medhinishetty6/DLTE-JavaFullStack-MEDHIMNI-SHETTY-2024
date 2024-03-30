package client;

import org.example.services.TransactionServices;
import org.example.web.Transaction;
import org.omg.IOP.TransactionService;

import java.util.List;

public class App {
    public static void main(String[] args) {
        TransactionService transactionService = new TransactionServices();
        Class<? extends TransactionService> transaction = transactionService.getTransaction();
        List<Transaction> transactionList = transaction("[{\"transactionAmount\":4500",\"transactionBalance\":500,\"transactionDate\":\"2024-02-22\", \"transactionId\":876543, \"transactionType\":\"Open\", \"userName\":\"Medhini\"}]");
    }
}
