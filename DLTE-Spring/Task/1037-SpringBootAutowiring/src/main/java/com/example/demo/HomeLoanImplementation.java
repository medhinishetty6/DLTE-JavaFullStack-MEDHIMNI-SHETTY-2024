package com.example.demo;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("homeLoan")
public class HomeLoanImplementation implements LoanInterface {
    @Override
    public List<Loan> findAll() {
        List<Loan> newList = new ArrayList<>();
        for (Loan each : loanList) {
            if (each.getLoanType().equals("home")) {
                newList.add(each);
//                return null;
            }
        }
        return newList;
    }
}
