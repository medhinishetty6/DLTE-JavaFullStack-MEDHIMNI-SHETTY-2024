package basics.service;
import java.util.Scanner;

public class MobileBanking {
    public  static void  main(String [] args){
        String userPIN=" ",dName=" ",dph=" ",dAcc=" ";
        Long userOTP=0L;
        Long amt=0L;
        Long transOTP=0L;
        Scanner scanner=new Scanner(System.in);
        System.out.println("----------Welcome to Mobile Banking-----------");
        System.out.println("Enter PIN");
        userPIN=scanner.nextLine();
        System.out.println("----------Enter user details-----------");
        System.out.println("Fill user name,user account number,user phone number");
        dName=scanner.nextLine();
        System.out.println("Enter amount");
        dAcc=scanner.nextLine();
        System.out.println("Enter UPI PIN");
        transOTP=scanner.nextLong();
        System.out.println("The amount of Rs"+dAcc+" is transfered to "+dName+" successfully");
    }

}
