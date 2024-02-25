package basics.loanProduct;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.Date;
import java.util.Scanner;

public class FLoan implements Bank {
    private static int TLoan;
    Loan loan[] = new Loan[5];

    @Override
    public void Add_New_Loan() {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner0 = new Scanner(System.in);
        for (int index = 0; index < TLoan; index++) {
            System.out.println("Enter the Loan number");
            Long Loan_Number = scanner.nextLong();
            System.out.println("Enter the Loan amount");
            Long Loan_Amount = scanner.nextLong();
            System.out.println("Enter the Loan date");
            String Loan_Date = scanner0.next();
            System.out.println("Enter the Loan status");
            String Loan_Status = scanner0.next();
            System.out.println("Enter the borrower name");
            String Borrower_Name = scanner0.next();
            System.out.println("Enter the borrower contact details");
            Long Borrower_Contact = scanner.nextLong();
            loan[index] = new Loan(Loan_Date, Loan_Status, Borrower_Name, Loan_Number, Loan_Amount, Borrower_Contact);
        }
    }

    @Override
    public void Check_Available_Loans() {
        for (int index = 0; index < loan.length; index++) {
            if (loan[index] != null) {
                if (loan[index].getLoan_Status().equalsIgnoreCase("open")) {
                    System.out.println("Loan of" + loan[index].getBorrower_Name() + "is still open");
                }
            }
        }
    }

    @Override
    public void Check_Closed_Loans() {
        for (int index = 0; index < loan.length; index++) {
            if (loan[index] != null) {
                if (loan[index].getLoan_Status().equalsIgnoreCase("closed")) {
                    System.out.println("Loan of" + loan[index].getBorrower_Name() + "is closed");
                }
            }
        }
    }
    public  static void  main(String[] args){
        FLoan loan_final=new FLoan();
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("1) Add loan\n 2)Check Loan\n 3)Check closed loan");
            int choice=scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter the number of loans");
                    TLoan=scanner.nextInt();
                    loan_final.Add_New_Loan();
                    break;
                case 2:
                    loan_final.Check_Available_Loans();
                    break;
                case 3:
                    loan_final.Check_Closed_Loans();
                    break;
                default:
                    System.exit(0);
            }
        }

    }
}