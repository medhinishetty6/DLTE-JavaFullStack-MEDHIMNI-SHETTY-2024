package com.spring.hqlsql.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRemote extends JpaRepository<Transaction,Integer> {
    @Query
            (value = "select * from transactions where user_name=:user and transaction_type=:type",nativeQuery = true)
    List<Transaction> findByUserAndType(String user, String type);
    @Query
            ("from Transaction where transactionAmount between :amount1 and :amount2")
    List<Transaction> findByRangeOfTransactionAmount(double amount1, double amount2);

}
