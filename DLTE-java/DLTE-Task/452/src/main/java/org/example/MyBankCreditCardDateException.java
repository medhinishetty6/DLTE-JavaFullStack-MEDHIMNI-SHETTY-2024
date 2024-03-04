package org.example;
import java.util.ResourceBundle;

public class MyBankCreditCardDateException extends RuntimeException {
    public MyBankCreditCardDateException(){
        super(ResourceBundle.getBundle("application").getString("exception.date"));
    }
}
