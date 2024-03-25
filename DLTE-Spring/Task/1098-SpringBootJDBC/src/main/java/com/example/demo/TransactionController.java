package com.example.demo;

import com.example.demo.Transaction;
import com.example.demo.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionServices transactionServices;
    @PostMapping("/new")
    public Transaction addTransaction(@RequestBody Transaction transaction){
        Transaction transaction1=transactionServices.apiSave(transaction);
        return transaction1;
    }

    @GetMapping("/send/{sender}")
    public List<Transaction> findBySender(@PathVariable("sender") String sender){
        return transactionServices.apiFindBySender(sender);
    }

    @GetMapping("/receive/{receiver}")
    public List<Transaction> findByReceiver(@PathVariable("receiver") String receiver){
        return transactionServices.apiFindByReceiver(receiver);
    }

    @GetMapping("/amount/{amount}")
    public List<Transaction> findBySender(@PathVariable("amount") Integer amount){
        return transactionServices.apiFindByAmount(amount);
    }
}
