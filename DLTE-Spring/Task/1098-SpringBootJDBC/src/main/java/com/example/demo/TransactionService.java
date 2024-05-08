package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TransactionService {
    @Autowired
    protected JdbcTemplate jdbcTemplate;
}
