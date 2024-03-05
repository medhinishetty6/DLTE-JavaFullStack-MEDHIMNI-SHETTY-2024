package org.example;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MobileBanking {
    public static void main(String[] args){
        Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        GPay gPay[]=new GPay[10];
        gPay[0]=new GPay(656132584L,845342.90,"Meghana",8296,"Medhini");
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        while (true){
            System.out.println("1.PayBills\n2.Exit");
            int choice=scanner.nextInt();
            switch (choice) {
                case 1:
                    try {
                        gPay[0].payBills("Madan", 87400.90, "Shopping");
                        break;
                    } catch (MyBankException exception) {
                        logger.log(Level.WARNING, exception.toString());
                    }
                case 2:
                    System.exit(0);
            }
            }
        }

    }

