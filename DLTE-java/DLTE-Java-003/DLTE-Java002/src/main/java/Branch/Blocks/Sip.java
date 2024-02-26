package Branch.Blocks;
import java.util.Scanner;
import java.lang.Math;
public class Sip{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        long invt;
        int years;
        double rate = 0,TAmount=0;
        System.out.println("Enter investment per mount");
        invt=sc.nextLong();
        System.out.println("Enter the time duration");
        years=sc.nextInt();
        System.out.println("Enter the intrest rate");
        rate=rate/(12*100);
        years *=12;
        TAmount = (invt*((Math.pow(1+rate,years)-1)/rate)*(1+rate));
        System.out.println("the output is"+TAmount);
    }
}