package org.example;

import java.io.IOException;

public interface Bank {
    void write() throws IOException;

    void read() throws IOException, ClassNotFoundException;
}
