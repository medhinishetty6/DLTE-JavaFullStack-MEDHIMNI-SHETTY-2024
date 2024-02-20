package Branch.Blocks;
import java.util.Scanner;

public class Slab{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        double Asalary,T;
        char regime;
        System.out.println("Enter the annual salary");
        Asalary=sc.nextDouble();
        System.out.println("Enter the type of regime \nType: \n'o'for old regime\n'n'for new regime");
        regime=sc.next().charAt(0);
        switch(regime){
            case 'o': {
                if (Asalary >= 0 && Asalary <= 250000) {
                    T = 0;
                    System.out.println("Tax payed" + T);
                } else if (Asalary >= 250000 && Asalary <= 550000) {
                    T = Asalary * 0.05;
                    System.out.println("Tax payed" + T);
                } else if (Asalary >= 550000 && Asalary <= 750000) {
                    T = Asalary * 0.20;
                    System.out.println("Tax payed" + T);
                } else if (Asalary >= 750000 && Asalary <= 1000000) {
                    T = Asalary * 0.20;
                    System.out.println("Tax payed" + T);
                } else if (Asalary >= 1000000 && Asalary <= 1250000) {
                    T = Asalary * 0.30;
                    System.out.println("Tax payed" + T);
                } else if (Asalary >= 1250000 && Asalary <= 1500000) {
                    T = Asalary * 0.30;
                    System.out.println("Tax payed" + T);
                } else {
                    T = Asalary * 0.30;
                    System.out.println("Tax payed" + T);
                }
                break;
            }
            case 'n': {
                if (Asalary >= 0 && Asalary <= 250000) {
                    T = 0;
                    System.out.println("Tax payed" + T);
                } else if (Asalary >= 250000 && Asalary <= 500000) {
                    T = Asalary * 0.05;
                    System.out.println("Tax payed" + T);
                } else if (Asalary >= 500000 && Asalary <= 750000) {
                    T = Asalary * 0.10;
                    System.out.println("Tax payed" + T);
                } else if (Asalary >= 750000 && Asalary <= 1000000) {
                    T = Asalary * 0.15;
                    System.out.println("Tax payed" + T);
                } else if (Asalary >= 1000000 && Asalary <= 1250000) {
                    T = Asalary * 0.20;
                    System.out.println("Tax payed" + T);
                } else if (Asalary >= 1250000 && Asalary <= 1500000) {
                    T = Asalary * 0.25;
                    System.out.println("Tax payed" + T);
                } else {
                    T = Asalary * 0.30;
                    System.out.println("Tax payed" + T);
                }
            }
        }
        System.out.println("--------Thank you----------");
    }
}
