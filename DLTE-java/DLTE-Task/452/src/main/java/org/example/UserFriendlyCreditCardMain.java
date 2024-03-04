package org.example;
import sun.security.mscapi.CPublicKey;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserFriendlyCreditCardMain {
    public static void main(String[] args) {
        UserFriendlyCreditCard[] creditCards = {
                new UserFriendlyCreditCard(65323568854L, "Medhini", new Date(2024, 12, 11), 654, 100000, new Date(2024, 4, 12), new Date(2024, 4, 29), 6543),
                new UserFriendlyCreditCard(98532356854L, "Sam", new Date(2024, 2, 3), 684, 10000, new Date(2024, 4, 27), new Date(2024, 4, 6), 6543),
                new UserFriendlyCreditCard(45323568854L, "Nahini", new Date(2024, 5, 4), 854, 170000, new Date(2024, 7, 12), new Date(2024, 8, 20), 6543),
                new UserFriendlyCreditCard(54321568854L, "Ray", new Date(2024, 6, 20), 454, 560000, new Date(2024, 9, 17), new Date(2024, 8, 29), 6543),};
        //creating object
        UserFriendlyCreditCardMain creditAnalysis = new UserFriendlyCreditCardMain();
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        //ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        System.out.println("Enter your choice\n 1 for filter on limit\n2 filter on date");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: {
                try {
                    creditAnalysis.filterLimit(creditCards, 70000, 500000);
                    break;
                } catch (MyBankCreditCardLimitException creditCardLimit) {
                    logger.log(Level.WARNING, creditCardLimit.toString());
                    creditAnalysis.filterLimit(creditCards, 70000, 500000);
                    break;
                }
            }
            case 2: {
                try {
                    creditAnalysis.filterdateOfBillPayment(creditCards, new Date(2024, 8, 20), new Date(2024, 10, 10));
                    break;
                } catch (MyBankCreditCardDateException dateOfBillPayment) {
                    logger.log(Level.WARNING, dateOfBillPayment.toString());
                    creditAnalysis.filterdateOfBillPayment(creditCards, new Date(2024, 8, 20), new Date(2024, 10, 10));
                    break;
                }
            }
        }
    }

    public void filterLimit(UserFriendlyCreditCard[] limit, int start, int end) {
        int flag = 0;
        for (UserFriendlyCreditCard each : limit) {
            if (each.getCreditCardLimit() >= start && each.getCreditCardLimit() <= end) {
                System.out.println("The credit card holder " + each.getCreditCardHOlder() + " is having the limit within the range " + start + " & " + end + "\n The limit is " + each.getCreditCardLimit());
            } else {
                //Exception
                throw new MyBankCreditCardLimitException();
            }
        }
    }

    public void filterdateOfBillPayment(UserFriendlyCreditCard[] dateofpayment, Date start, Date end) {
        for (UserFriendlyCreditCard each : dateofpayment) {
            if (each.getDateOfBillPayment().before(end) && each.getDateOfBillPayment().after(start)) {
                System.out.println("The date of payment ranges from " + start.getDate() + " to" + end.getTime());
                System.out.println("The credit card holder " + each.getCreditCardHOlder() + "has made on-time payment \nThe date of payment is: "+each.getDateOfBillPayment().getDate());
            } else {
                //Exception
                throw new MyBankCreditCardDateException();
            }
        }
    }
}



