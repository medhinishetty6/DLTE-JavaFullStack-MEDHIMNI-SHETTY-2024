package org.example;
import org.omg.CORBA.TRANSACTION_MODE;

import java.util.Date;
import java.util.Scanner;

public class AnalysisTransaction implements Runnable {
    Transaction[] transaction = {
            new Transaction(new Date(2024, 01, 21), 100.0, "Medh", "family"),
            new Transaction(new Date(2024, 02, 12), 200.0, "peh", "Education"),
            new Transaction(new Date(2024, 01, 05), 300.0, "poh", "Emergency"),
            new Transaction(new Date(2024, 03, 01), 400.0, "bob", "Education"),
    };

    public void run() {
        AnalysisTransaction analysisTransaction = new AnalysisTransaction();
        Scanner scanner = new Scanner(System.in);
        int choice;
        //transcation menu
        while (true) {
            System.out.println("---------------Transaction Analysis-----------");
            System.out.println("Enter your choice");
            System.out.println("1.Filter based on given ranges of date\n" +
                    "2.least amount transferred\n" +
                    "3.maximum amount transferred\n" +
                    "4.number of transaction made to particular beneficiary\n" +
                    "5.filter based on particular remarks");
            choice = scanner.nextInt();
            switch (choice) {
                case1:
                    analysisTransaction.BasedOnRangeOfDate(transaction);
                    return;
                case2:
                    analysisTransaction.LeastAmountTransferred(transaction);
                    return;
                case 3:
                    analysisTransaction.MaxAmountTransferred(transaction);
                    return;
                case 4:
                    analysisTransaction.TransactionMadeToParticularBeneficiary(transaction);
                    return;
                case 5:
                    analysisTransaction.FilterBasedOnParticularRemarks(transaction);
                    return;
            }


        }

    }

    public void BasedOnRangeOfDate(Transaction[] transaction) {
        String startdate;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the start date");
        startdate = scanner.next();
        String splitstartdate[] = startdate.split("/");
        for (Transaction each : transaction) {
            if ((Integer.parseInt(splitstartdate[0]) == (each.getDateoftransaction()).getDate()) && (Integer.parseInt(splitstartdate[1]) == (each.getDateoftransaction()).getMonth()) && (Integer.parseInt(splitstartdate[2]) == (each.getDateoftransaction()).getYear())) {
                System.out.println("date of transactioin " + (each.getDateoftransaction()).getDate() + "to " + each.getTo());
                System.out.println(each.getTransactionamount());
            }
        }
    }

    public void LeastAmountTransferred(Transaction[] transaction) {
        double leastAmount = Integer.MAX_VALUE;
        for (Transaction each : transaction) {
            double compare = each.getTransactionamount();
            if (compare < leastAmount) {
                leastAmount = compare;
            }

        }
        System.out.println("least amount transfered is " + leastAmount);
    }

    public void MaxAmountTransferred(Transaction[] transaction) {
        double maxAmount = Integer.MIN_VALUE;
        for (Transaction each : transaction) {
            double compare = each.getTransactionamount();
            if (compare < maxAmount) {
                maxAmount = compare;
            }

        }
        System.out.println("least amount transfered is " + maxAmount);
    }

    public void TransactionMadeToParticularBeneficiary(Transaction[] transaction) {
        Scanner scanner = new Scanner(System.in);
        String person;
        System.out.println("Enter beneficiary name");
        person = scanner.next();
        int transactionNo = 0;
        for (Transaction each : transaction) {
            if (each.getTo().equals(person)) {
                transactionNo++;
            }
        }
        System.out.println("total number of transcation to " + person + " : " + transactionNo);
    }

    public void FilterBasedOnParticularRemarks(Transaction[] transaction) {
        Scanner scanner = new Scanner(System.in);
        String perticularRemark; //family,education,emergency
        System.out.println("Enter remark");
        perticularRemark = scanner.next();
        int transactionNo = 0;
        for (Transaction each : transaction) {
            if (each.getRemarks().equals(perticularRemark)) {
                System.out.println(each.getTransactionamount() + " amount transfered to " + each.getTo() + " for " + perticularRemark);
            }
        }
    }
    public void displayRemarks(){
        System.out.println("Remarks in transaction");
        for(Transaction each: transaction){
            System.out.println(each.getRemarks());
        }
    }

    public void displayAmount() {
        System.out.println("Amount transefered");
        for (Transaction each : transaction) {
            System.out.println(each.getTransactionamount());
        }
    }

    public void displayTransactionTo() {
        System.out.println("Names to whom the money is transefered ");
        for (Transaction each : transaction) {
            System.out.println(each.getTo());
        }
    }

}