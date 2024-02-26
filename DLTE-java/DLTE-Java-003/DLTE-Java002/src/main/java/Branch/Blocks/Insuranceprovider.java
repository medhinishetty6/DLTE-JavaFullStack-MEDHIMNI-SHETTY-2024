package Branch.Blocks;
import  java.util.*;

public class Insuranceprovider {
    public static void  main(String[] args){
     String[] WeInc={"good customer service,","sharing risk"};
     String[] LifeInc={"accident claims","sharing risk"};
     String[] HInc={"sharing risk","good customer service"};
     String userneed="";
     Scanner scanner=new Scanner(System.in);
     System.out.println("Enter user need");
     userneed=scanner.nextLine();
     System.out.println(userneed);
     if(WeInc.equals(userneed))
     {
            System.out.println("user can have WeInc insurance policy");
        }
        if(LifeInc.equals(userneed))
        {
            System.out.println("user can have LifeInc insurance policy");
        }
        if(HInc.equals(userneed))
        {
            System.out.println("user can have HInc insurance policy");
        }

    }
    }
