package org.example;

import java.util.Date;

public class MAIN {
      public static void main(String[] args){
          MyBankDatabase<Transaction>  transactionObject=new MyBankDatabase<>();
          MyBankDatabase<CreditCard>  creditCardObject=new MyBankDatabase<>();
          transactionObject.newDataBase=new Transaction[3];
          creditCardObject.newDataBase=new CreditCard[3];
          Transaction transaction=new Transaction(new Date(2024,01,21), 100.0, "Medh", "family");
          Transaction transaction1=new Transaction(new Date(2024,02,12), 200.0, "Megha", "Education");
          CreditCard creditCard=new CreditCard("Medhini",526398745L,963,new Date(2024,01,05),50000,7253,new Date(2024,02,17),new Date(2024,02,20));
          CreditCard creditCard1=new CreditCard("Meghana",985321467L,941,new Date(2024,02,05),60000,6233,new Date(2024,01,17),new Date(2024,01,20));
         //Generic implementation for transaction
          System.out.println(transactionObject.insert(transaction));
          System.out.println(transactionObject.insert(transaction1));
          System.out.println(transactionObject.read(1));
          transactionObject.update(1,new Transaction(new Date(2024,02,12), 200.0, "Megha", "Education"));
          System.out.println(transactionObject.read(1));
          System.out.println(transactionObject.delete(1));
          //Generic implementation for creditCard
          System.out.println(creditCardObject.insert(creditCard));
          System.out.println(creditCardObject.insert(creditCard1));
          System.out.println(creditCardObject.read(1));
          creditCardObject.update(1,new CreditCard("Medhini",526398745L,963,new Date(2024,01,05),50000,7253,new Date(2024,02,17),new Date(2024,02,20)));
          System.out.println(creditCardObject.read(1));
          System.out.println(creditCardObject.delete(1));


      }
}
