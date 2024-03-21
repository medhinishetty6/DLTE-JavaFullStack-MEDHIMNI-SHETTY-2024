package com.example.demo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface LoanInterface {
    List<Loan> loanList= Stream.of(new Loan(763487L,59000.0, new Date(2024,02,14),"Open","Medhini",983452763L,"personal"),
                                   new Loan(983546L,66000.0, new Date(2024,01,14),"Close","Meghana",8147533826L,"home")).collect(Collectors.toList());
    List<Loan> findAll();
}
