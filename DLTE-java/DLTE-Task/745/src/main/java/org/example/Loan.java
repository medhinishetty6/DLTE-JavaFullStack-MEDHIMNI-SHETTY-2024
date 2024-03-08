package org.example;

import java.util.Date;

public class Loan {
   private int loanNumber;
   private Double loanAmount;
   private Date loanDate;
   private String loanStatus;
   private String borrowerName;
   private Long borrowerContact;

   public Loan(int loanNumber, Double loanAmount, Date loanDate, String loanStatus, String borrowerName, Long borrowerContact) {
      this.loanNumber = loanNumber;
      this.loanAmount = loanAmount;
      this.loanDate = loanDate;
      this.loanStatus = loanStatus;
      this.borrowerName = borrowerName;
      this.borrowerContact = borrowerContact;
   }
}
