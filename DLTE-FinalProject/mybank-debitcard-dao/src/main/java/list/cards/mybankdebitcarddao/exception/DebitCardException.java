package list.cards.mybankdebitcarddao.exception;

import java.util.ResourceBundle;

//Debit card Exception handling
public class DebitCardException extends RuntimeException{
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public DebitCardException(String message){
        super(message);
    }

}



