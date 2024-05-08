package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServices extends TransactionService {


    public Transaction apiSave(Transaction transaction) {
        int acknowledge = jdbcTemplate.update("insert into transaction() values(?,?,?,?,?,?)",
                new Object[]{
                        transaction.getTransactionId(),
                        transaction.getTransactionDate(),
                        transaction.getTransactionTo(),
                        transaction.getTransactionAmount(),
                        transaction.getTransactionRemarks(),
                        transaction.getTransactionBy(),
                });

        return transaction;
    }

    public List<Transaction> apiFindBySender(String sender) {
        List<Transaction> myCards = (List<Transaction>) jdbcTemplate.query("select * from transaction where transaction_by=?",
                new Object[]{sender},
                new BeanPropertyRowMapper<>(Transaction.class));
        return myCards;
    }


    public List<Transaction> apiFindByReceiver(String receiver) {
        List<Transaction> myCards = (List<Transaction>) jdbcTemplate.query("select * from transaction where transaction_to=?",
                new Object[]{receiver},
                new BeanPropertyRowMapper<>(Transaction.class));
        return myCards;
    }

    public List<Transaction> apiFindByAmount(Integer amount) {
        List<Transaction> myCards = (List<Transaction>) jdbcTemplate.query("select * from transaction where transaction_amount=?",
                new Object[]{amount},
                new BeanPropertyRowMapper<>(Transaction.class));
        return myCards;
    }

    public String removeByDate(Date start, Date end) {
        int ack = jdbcTemplate.update("delete from transactions_model where transaction_date between ? and ?", new Object[]{start, end});
        if (ack != 0)
            return "removed";
        else
            return null;
    }

    private class CardMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transactionsModel = new Transaction();
            transactionsModel.setTransactionId(rs.getLong(1));
            transactionsModel.setTransactionDate(rs.getDate(2));
            transactionsModel.setTransactionBy(rs.getString(3));
            transactionsModel.setTransactionTo(rs.getString(4));
            transactionsModel.setTransactionAmount(rs.getInt(5));
            transactionsModel.setTransactionRemarks(rs.getString(6));
            return transactionsModel;
        }
    }
}

