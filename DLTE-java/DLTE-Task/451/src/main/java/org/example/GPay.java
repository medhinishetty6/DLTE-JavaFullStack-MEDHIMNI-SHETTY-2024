package org.example;

import java.util.Scanner;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GPay extends Account{
    private Integer UPIPin;
    private  String username;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public GPay(Long accountNumber, Double accountBalance, String accountHolder,Integer UPIPin, String username) {
        super(accountNumber,accountBalance,accountHolder);
        this.UPIPin = UPIPin;
        this.username = username;
    }

    public boolean validatePin(String newPin) throws MyBankException{
        if(!UPIPin.equals(newPin)){
            logger.log(Level.WARNING,"Invalid  Pin entered");
            throw new MyBankException("pin.invalid");
        }
        return true;
    }
    public void payBills(String billerName, double billAmount, String billType, String UPIPin ){
        try{
            validatePin(UPIPin);
            if(getAccountBalance()>=billAmount){
                logger.log(Level.INFO,"payment successful to " +billerName+ " of amount " +billAmount+ " for " +billType );
            }
            else{
                logger.log(Level.WARNING,"Insufficient balance");
                throw new MyBankException("pin.invalid");
            }
        }catch (MyBankException exception){
            logger.log(Level.WARNING,exception.toString());
            throw exception;
        }
    }
}