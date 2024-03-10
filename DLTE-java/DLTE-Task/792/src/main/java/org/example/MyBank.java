package org.example;

import java.io.IOException;
import java.util.ArrayList;

public interface MyBank {
    ArrayList<Loan> loan=new ArrayList<>(25);
    void write() throws IOException;
    void read() throws IOException,ClassNotFoundException;
    void addLoan(Loan loan);
    void Availibility();
    void closedLoan();
}

