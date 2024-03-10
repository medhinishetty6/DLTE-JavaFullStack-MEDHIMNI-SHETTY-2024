package org.example;

import java.io.IOException;

public interface MyBank1 extends Product, Bank {
    @Override
    void write() throws IOException;

    @Override
    void read() throws IOException, ClassNotFoundException;

    void Availability();

    void closedLoan();
}
