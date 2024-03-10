package org.example;

import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.MyBank.loan;

public class MAIN {
    public static void main(String[] args){
        MAIN main=new MAIN();
        Loan loan1=new Loan(895648123L,900.00,new Date(2024,01,29),"Open","Medhini",8956231452L);
        Loan loan2=new Loan(123587452L,9900.00,new Date(2024,01,19),"Closed","Meghana",5566332211L);
        Loan loan3=new Loan(963452661L,800.00,new Date(2024,03,07),"Open","Madan",8452156522L);
        Loan loan4=new Loan(632876509L,400.00,new Date(2024,02,27),"Open","Prajothi",8958908776L);
        loan.addAll(Stream.of(loan1,loan2,loan3,loan4).collect(Collectors.toList()));
        MyBank filter =((start, end) -> {
            for(Loan each: loan){
                if(each.getLoanDate().after(start) && each.getLoanDate().before(end)){
                    System.out.println(each);
                }
            }
        });

        System.out.println("Enter the date to filter");
        Scanner scanner=new Scanner(System.in);
        Date start=new Date(scanner.next());
        System.out.println("Enter the end date");
        Date end=new Date(scanner.next());
        filter.filterDate(start,end);
    }

}
