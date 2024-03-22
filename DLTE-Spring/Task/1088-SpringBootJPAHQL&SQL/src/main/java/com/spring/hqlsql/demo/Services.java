package com.spring.hqlsql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services {
        @Autowired
        TransactionRemote transactionRemote;

        public Transaction newTransactions(Transaction transactions) {
            return transactionRemote.save(transactions);
        }

        public List<Transaction> findAllByUserAndType(String user, String type) {
            return transactionRemote.findByUserAndType(user, type);

        }
        public List<Transaction>findAllByRange(double amount1,double amount2){
            return transactionRemote.findByRangeOfTransactionAmount(amount1,amount2);
        }

    }

