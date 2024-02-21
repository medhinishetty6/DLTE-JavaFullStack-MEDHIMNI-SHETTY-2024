package basics.service;
import java.util.Scanner;
// Command line

/*
Personal Details: name, aadhaar, pan, address,mobile, email
Income: salaried, self-employed: ITR
*/
public class PersonalLoan {
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
        System.out.println("Enter the email address");
        borrowerAddress=scanner.nextLine();
        System.out.println("Dear"+borrowerName+"Thanks for showing interest on taking the personal loan further details will be sent to your registered mobile number");
    }
}
