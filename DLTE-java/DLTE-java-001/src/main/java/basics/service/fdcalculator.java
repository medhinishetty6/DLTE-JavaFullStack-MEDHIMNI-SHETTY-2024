package basics.service;
import java.util.*;

public class fdcalculator {
    public static void main(String[] args){
        Float intrest,maturityamount;
        int year=0;
        float rate;
        Long Principleamount=0L,Interestrate=0L;
        Scanner scanner=new Scanner(System.in);{
        System.out.println("Enter the Priciple amount");
        Principleamount=scanner.nextLong();
        System.out.println("Enter the Intrest rate");
        Interestrate=scanner.nextLong();
        System.out.println("Enter the number of years");
        year=scanner.nextInt();
        rate=(float)Interestrate/100;
        intrest=(float)Principleamount*rate*year;
        maturityamount=(float)Principleamount+intrest;
        System.out.println("Interest earner:"+intrest+"\n Total return for amount "+Principleamount+" after "+year+" years is Rs: "+maturityamount);
        }
    }

}
