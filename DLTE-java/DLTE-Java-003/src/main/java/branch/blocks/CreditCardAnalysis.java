package branch.blocks;
import com.sun.istack.internal.NotNull;

import java.util.Date;
import java.util.Scanner;
import static  java.lang.System.exit;
public class CreditCardAnalysis {
    public static void main(String[] args) {
        creditcard[] myBank = {
                new creditcard(65323568854L, "Medhini", new Date(2033, 12, 30), 654, 100000, new Date(2024, 4, 12), new Date(2024, 4, 29), 6543),
                new creditcard(98532356854L, "Sam", new Date(2033, 12, 30), 654, 100000, new Date(2024, 4, 12), new Date(2024, 4, 29), 6543),
                new creditcard(45323568854L, "Nahini", new Date(2033, 12, 30), 654, 100000, new Date(2024, 4, 12), new Date(2024, 4, 29), 6543),
                new creditcard(54321568854L, "Ray", new Date(2033, 12, 30), 654, 100000, new Date(2024, 4, 12), new Date(2024, 4, 29), 6543),
        };
        while (true) {
            System.out.println("----Credit Card Analysis------");
            System.out.println("Choose 1 to find customer card limit\n" + "Choose 2 to find date of bill payment\n" + "Choose 3 for updating pin number\n" + "Choose 4 to update limit of bill generation\n" + "Choose 5 to exit");
            int choice;
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            CreditCardAnalysis analysis = new CreditCardAnalysis();
            switch (choice) {
                case 1:
                    analysis.FindCardLimit(myBank);
                    break;
                case 2:
                    System.out.println("Enter the start date");
                    int StartDate = scanner.nextInt();
                    System.out.println("Enter the start date");
                    int EndDate = scanner.nextInt();
                    analysis.FindDateOfBillPayment(myBank, StartDate, EndDate);
                    break;
                case 3:
                    analysis.UpdatePIN(myBank);
                    break;
                case 4:
                    analysis.UpdateLimit(myBank);
                case 5:
                    exit(0);
            }
        }
    }

    public void FindCardLimit(creditcard customer) {
        Long StartLimit, EndLimit;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the start range ");
        StartLimit = scanner.nextLong();
        System.out.println("Enter the end limit");
        EndLimit = scanner.nextLong();
        for (creditcard each : customer) {
            if (each.getCreditCardLimit() >= StartLimit && each.getCreditCardLimit() <= EndLimit) {
                System.out.println(each.getCreditCardHolder() + " has a limit of " + each.getCreditCardLimit() + " ");
            }

        }
    }

    public void FindDateOfBillPayment(creditcard[] customer, int start, int end) {
        System.out.println(" Customers who made bill payments between " + start + "and" + end);
        for (creditcard each : customer) {
            if (each.getBillGenerationDate().getDate() >= start && each.getBillGenerationDate().getDate() <= end) {
                System.out.println(each.getCreditCardHolder() + " " + each.getBillGenerationDate().getDate());

            }
        }
    }
    public void UpdatePIN(creditcard[] customers){
        int currentPin, newPin;
        Long creditCardNumber=0L;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your card number to change your password");
        creditCardNumber = scanner.nextLong();
        for(creditcard each: customers){
            if(each.getCreditCardLimit().equals(creditCardNumber)){
                System.out.println("Enter your PIN");
                currentPin =scanner.nextInt();
                if(currentPin ==each.getPin()){
                    System.out.println("Enter your updated pin");
                            newPin=scanner.nextInt();
                    each.setPin(newPin);
                    System.out.println("your pin has been updated successfully");
                }else {
                    System.out.println("Entered pin is wrong");
                }
            }
        }

    }
    public void UpdateLimit(creditcard[] customer){
        double newCardLimit;
        int RoundOfNewCardLimit;
        for (creditcard each: customer){
            if(each.getBillGenerationDate().getDate()==5){
                newCardLimit=(each.getCreditCardLimit()*0.05)+ each.getCreditCardLimit();
                RoundOfNewCardLimit=(int) Math.round(newCardLimit);
                each.setCreditCardLimit(RoundOfNewCardLimit);
                System.out.println("Successfully updated");
            }
        }
    }
}

