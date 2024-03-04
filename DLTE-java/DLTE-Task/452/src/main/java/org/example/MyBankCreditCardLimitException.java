package org.example;
import java.util.ResourceBundle;

public class MyBankCreditCardLimitException extends  RuntimeException {
    public MyBankCreditCardLimitException(){
        super(ResourceBundle.getBundle("application").getString("exception.credit"));
    }
}
