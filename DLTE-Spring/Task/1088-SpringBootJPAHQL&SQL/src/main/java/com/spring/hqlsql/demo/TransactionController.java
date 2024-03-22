package com.spring.hqlsql.demo;

import javafx.concurrent.Service;
import jdk.vm.ci.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Controller")
public class TransactionController {
    @Autowired
    Services services;
    @PostMapping(value = "/create" ,consumes = "application/xml",produces = "application/xml")
    public Transaction callNewTransaction(@RequestBody Transaction transactions){
        return services.(transactions);
    }

    @GetMapping("/findByUserAndType/{name}/{type}")
    public List<Transaction> callFindTransactions(@PathVariable("name") String name, @PathVariable("type") String type){
        return services.findAllByUserAndType(name, type);
    }

    @GetMapping("/findByAmount /{amount1}/{amount2}")
    public List<Transaction> callFindByAmountRange(@PathVariable("amount1") double amount1, @PathVariable("amount2") double amount2){
        return services.findAllByRange(amount1,amount2);
    }




}
