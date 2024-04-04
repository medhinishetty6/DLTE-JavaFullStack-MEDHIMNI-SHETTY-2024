package com.example.demo.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@Service
public class TransactionService {

         @Autowired
         private JdbcTemplate jdbcTemplate;

    // New transaction
    public Transaction newTransaction(Transaction transaction) {
        int acknowledge = jdbcTemplate.update("INSERT INTO transaction (transaction_id,transaction_date, transaction_to, transaction_amount, transaction_remarks, transaction_by) VALUES (?, ?, ?, ?, ?, ?)",
                new Object[]{transaction.getTransactionId(),transaction.getTransactionDate(), transaction.getTransactionTo(), transaction.getTransactionAmount(), transaction.getTransactionRemarks(), transaction.getTransactionBy()});
        if (acknowledge != 0)
            return transaction;
        else
            return null;
    }

    // Find transactions by sender
    public List<Transaction> findBySender(String sender) {
        return jdbcTemplate.query("SELECT * FROM transaction WHERE transaction_by = ?", new Object[]{sender}, new TransactionMapper());
    }

    // find transactions by receiver
    public List<Transaction> findByReceiver(String receiver) {
        return jdbcTemplate.query("SELECT * FROM transaction WHERE transaction_to = ?", new Object[]{receiver}, new TransactionMapper());
    }

    // Transactions by amount
    public List<Transaction> findByAmount(double amount) {
        return jdbcTemplate.query("SELECT * FROM transaction WHERE transaction_amount = ?", new Object[]{amount}, new TransactionMapper());
    }

    // Update remarks of a transaction
    public Transaction updateRemarks(Transaction transaction) {
        int acknowledge = jdbcTemplate.update("UPDATE transaction SET transaction_remarks = ? WHERE transaction_id = ?",
                new Object[]{transaction.getTransactionRemarks(), transaction.getTransactionId()});
        if (acknowledge != 0)
            return transaction;
        else
            return null;
    }

    // Remove transactions between given dates
    public String removeTransactionsBetweenDates(Date startDate, Date endDate) {
        int count = jdbcTemplate.update("DELETE FROM transaction WHERE transaction_date BETWEEN ? AND ?", startDate, endDate);
        return count + " transactions removed between " + startDate + " and " + endDate;
    }

    // RowMapper class to map rows of ResultSet to Transaction objects
    public class TransactionMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setTransactionId(rs.getInt("transaction_id"));
            transaction.setTransactionDate(rs.getDate(
                    "transaction_date"));
            transaction.setTransactionTo(rs.getString("transaction_to"));
            transaction.setTransactionAmount(rs.getDouble("transaction_amount"));
            transaction.setTransactionRemarks(rs.getString("transaction_remarks"));
            transaction.setTransactionBy(rs.getString("transaction_by"));
            return transaction;
        }
    }
}


