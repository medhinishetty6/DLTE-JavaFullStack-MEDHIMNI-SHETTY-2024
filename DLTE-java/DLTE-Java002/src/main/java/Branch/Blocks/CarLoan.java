package Branch.Blocks;
import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.*;


public class CarLoan {
    public static void main(String[] args){
       String borrowerName,borrowerPan,borrowerAddress,borrowerincometype,borrowerEmail;
       double salary;
       Long aadhaar=0L,mobile=0L;
       Scanner sc=new Scanner(System.in);
       System.out.println("-------Welcome to our Bank--------");
        System.out.println("Enter your name");
        borrowerName=sc.nextLine();
        System.out.println("Enter the Income type(self/Salary)");
        borrowerincometype=sc.nextLine();
        System.out.println("Enter your Salary");
        salary=sc.nextDouble();
        System.out.println("Enter your email");
        borrowerEmail=sc.next();
        System.out.println("Enter your PAN no");
        borrowerPan=sc.next();
        System.out.println("Enter your Aadhaar no");
        aadhaar=sc.nextLong();
        System.out.println("Enter your address");
        borrowerAddress=sc.next();
        System.out.println("Enter your mobile no");
        mobile=sc.nextLong();
        Pattern pattern0=Pattern.compile("^\\d{12}");
        String value=String.valueOf(aadhaar);
        Matcher matcher0=pattern0.matcher(value);
        if(matcher0.matches())
        {
            System.out.println("Aadhaar "+aadhaar+" is valid");
        }else {
            System.out.println("Aadhaar "+aadhaar+" is In-valid");
        }
        Pattern pattern1=Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
        Matcher matcher1=pattern1.matcher(borrowerPan);
        if(matcher1.matches())
        {
            System.out.println("PAN ID "+borrowerPan+" is valid");
        }else {
            System.out.println("PAN ID "+borrowerPan+" is In-valid");
        }
        Pattern pattern2=Pattern.compile("^\\d{10}$");
        String value1=String.valueOf(mobile);
        Matcher matcher2=pattern2.matcher(value1);
        if(matcher2.matches())
        {
            System.out.println("Mobile no "+mobile+" is valid");
        }else {
            System.out.println("Mobile no "+mobile+" is In-valid");
        }

System.out.println("Dear Customer"+borrowerName+"Thanks for taking car loan in our bank the details will be mailed to your email or sms to mobile");
    }
}
