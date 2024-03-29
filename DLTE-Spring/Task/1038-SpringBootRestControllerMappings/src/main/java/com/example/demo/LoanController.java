package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private List<Loan> loanList=new ArrayList<>();
    public LoanController(){
        loanList.add(new Loan(1, "Medhini", 9700.0));
        loanList.add(new Loan(2, "Meghana", 8500.0));
        loanList.add(new Loan(3, "Madan", 45000.0));
    }
    @GetMapping("/{index}")
    public Loan getLoan(@PathVariable int index) {
        if (index >= 0 && index < loanList.size()) {
            return loanList.get(index);
        } else {
            throw new IllegalArgumentException("Invalid index");
        }
    }
    @PostMapping("/add")
    public Loan addLoan(@RequestBody Loan loan) {
        loanList.add(loan);
        return loan;
    }
}
