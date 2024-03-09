package org.example;

import java.util.Date;

public class Transaction {
       private Date dateoftransaction;
       private  Double transactionamount;
       private  String  to;
       private  String remarks=" ";


       public Date getDateoftransaction() {
              return dateoftransaction;
       }

       public void setDateoftransaction(Date dateoftransaction) {
              this.dateoftransaction = dateoftransaction;
       }

       public Double getTransactionamount() {
              return transactionamount;
       }

       public void setTransactionamount(Double transactionamount) {
              this.transactionamount = transactionamount;
       }

       public String getTo() {
              return to;
       }

       public void setTo(String to) {
              this.to = to;
       }

       public String getRemarks() {
              return remarks;
       }

       public void setRemarks(String remarks) {
              this.remarks = remarks;
       }

       @Override
       public String toString() {
              return "Transaction{" +
                      "dateoftransaction=" + dateoftransaction +
                      ", transactionamount=" + transactionamount +
                      ", to='" + to + '\'' +
                      ", remarks='" + remarks + '\'' +
                      '}';
       }

       public Transaction(Date dateoftransaction, Double transactionamount, String transactionto, String remarks) {
              this.dateoftransaction = dateoftransaction;
              this.transactionamount = transactionamount;
              this.to = to;
              this.remarks = remarks;
       }

}

