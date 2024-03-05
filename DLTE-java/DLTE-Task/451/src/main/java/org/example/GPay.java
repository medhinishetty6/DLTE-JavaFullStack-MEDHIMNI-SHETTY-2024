package org.example;

import java.util.Scanner;

public class GPay extends Account{
    private Integer upiPin;
    private  String username;

    public GPay(Long accountNumber, Double accountBalance, String accountHolder,Integer upiPin, String username) {
        super(accountNumber,accountBalance,accountHolder);
        this.upiPin = upiPin;
        this.username = username;
    }

    public Integer getUpiPin() {
        return upiPin;
    }

    public void setUpiPin(Integer upiPin) {
        this.upiPin = upiPin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void payBills(String billerName,Double billedAmount,String billerType){
        int pinnumber=0;
        int count=0;
        while (count<5){
            System.out.println("Enter the Pin");
            Scanner scanner = new Scanner(System.in);
            pinnumber = scanner.nextInt();
            if(pinnumber==getUpiPin()) {
                if (billedAmount <= getAccountBalance()) {
                    System.out.println("Bill paid Details:" + "Biller name: " + billerName + "Bill amount" + billedAmount + "Bill type: " + billerType);
                    setAccountBalance(getAccountNumber() - billedAmount);
                    System.out.println(getAccountBalance());
                    break;
                } else {
                    System.out.println("Insufficient balance");
                }
            }else{
                  count++;
                }
        }
        if(count>=5){
            throw new MyBankException();
        }
    }
}
