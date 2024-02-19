package basics.service;
// Command line

import java.util.Scanner;

/*
Personal Details: name, aadhaar, pan, address,mobile, email
Income: salaried, self-employed: ITR
*/
public class Interactions {
    public  static void main(String[] args){
        String borrowerName="", borrowerPan="", borrowerAddress="", borrowerEmail="", borrowerIncometype="";
        Long mobileNumber=0L, aadhaar=0L;
        Scanner scanner=new Scanner(System.in);
        System.out.println("-----------Welcome to MyBank------------");
        System.out.println("Fill your name");
        borrowerName=scanner.nextLine();
        System.out.println("Fill your Aadhaar details");
        aadhaar=scanner.nextLong();
        System.out.println("Enter the pan");
        borrowerPan=scanner.next();
        System.out.println("Let us know income type(Salaried/self-employed");
        borrowerIncometype=scanner.next();
        System.out.println("Mention the mobile number");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter the mail address");
        borrowerAddress=scanner.nextLine();
        System.out.println("Thanks for showing interest on taking the car loan further details will be sent to your registered mobile number");
    }
}
