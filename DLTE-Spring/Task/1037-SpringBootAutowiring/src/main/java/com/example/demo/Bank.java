package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Bank {
    @Autowired
    @Qualifier("homeLoan")
    private LoanInterface loanInterface;
    public List<Loan> callFindAll() {
        List<Loan> homeLoanList=loanInterface.findAll();
        //return loanInterface.findAll();
        return homeLoanList;
    }
}
