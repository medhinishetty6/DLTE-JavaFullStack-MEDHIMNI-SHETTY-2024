package org.example;

import java.util.ResourceBundle;

public class Exception extends RuntimeException{
    public Exception() {
        System.out.println(ResourceBundle.getBundle("application").getString("class.bank"));

    }
}
