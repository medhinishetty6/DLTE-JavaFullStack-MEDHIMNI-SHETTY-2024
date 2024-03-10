package org.example;

import java.io.IOException;
import java.util.ArrayList;

interface MyBank {

    ArrayList<Loan> loan = new ArrayList<>(20);

    void write() throws IOException;

    void read() throws IOException, ClassNotFoundException;

    void addNewLoan(Loan loan);

    void Availibility();

    void ClosedLoan();

    void Availability();

    void closedLoan();
}
