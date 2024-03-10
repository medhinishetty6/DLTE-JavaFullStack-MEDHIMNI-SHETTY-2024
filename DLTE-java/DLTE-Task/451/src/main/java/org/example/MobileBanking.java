package org.example;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.GPay;
import org.example.MyBankException;

public class MobileBanking {
    public static void main(String[] args){
        Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        GPay gPay[]=new GPay[10];
        gPay[0]=new GPay(656132584L,845342.90,"Meghana",8296,"Medhini");
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        int choice=0;
        while(choice<5){
            System.out.println("Enter the biller name");
            String billerName=scanner.next();
            System.out.println("Enter the bill amount");
            Double billAmount=scanner.nextDouble();
            System.out.println("Enter the bill type");
            String billType=scanner.next();
            try{
                System.out.println("Enter the pin number");
                String pin=scanner.next();
                gPay.payBills(billerName,billAmount,billType,pin);
                choice=0;
                return;
            } catch(MyBankException exception){
                logger.log(Level.WARNING,exception.toString());
                choice++;
                if(choice>=5){
                    logger.log(Level.WARNING,"Account blocked.Contact your bank!");
                    break;
                }
            }

        }
        scanner.close();
    }
}

