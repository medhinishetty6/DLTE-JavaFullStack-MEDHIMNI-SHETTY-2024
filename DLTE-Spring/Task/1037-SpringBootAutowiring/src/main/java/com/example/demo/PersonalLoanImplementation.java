package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("personal")
public class PersonalLoanImplementation implements LoanInterface {
    @Override
    public List<Loan> findAll() {
        List<Loan> newList = new ArrayList<>();
        for (Loan each : loanList) {
            if (each.getLoanType().equals("personal")) {
                newList.add(each);
            }
        }
        return newList;
    }
}