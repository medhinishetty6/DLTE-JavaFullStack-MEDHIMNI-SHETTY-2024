package basics.service;
import java.util.Scanner;

public class Internetbanking {
    public static void main(String [] args){
    String userName="",usercode="",userPass=" ",useremail=" ",Resipientname="",ResipientAcc="",Resipientph="";
    int userotp,transferotp;
    Long  amount=0L;
    Scanner scanner=new Scanner(System.in);
    System.out.println("------------Welcome to Internet banking------------");
        System.out.println("Fill your name");
        userName=scanner.nextLine();
        System.out.println("Fill your Password");
        userPass=scanner.nextLine();
        System.out.println("Enter the email address");
        useremail=scanner.nextLine();
        System.out.println("Enter the otp");
        userotp=scanner.nextInt();
        System.out.println("Enter two step verification code");
        usercode =scanner.next();
        System.out.println("Dear"+userName+"Thanks for showing your interest in internet banking");
        System.out.println("Enter Recient details");
        System.out.println("Fill Recipient name");
        Resipientname=scanner.next();
        System.out.println("Fill Recipient account number");
        ResipientAcc=scanner.next();
        System.out.println("Enter Recipient phone number");
        Resipientph=scanner.next();
        System.out.println("Enter Amount");
        amount=scanner.nextLong();
        System.out.println("Enter otp");
        transferotp =scanner.nextInt();
        System.out.println("Dear"+userName+"the amount of Rs"+amount+"has been transferred to"+Resipientname+"Successfully");
    }
}
