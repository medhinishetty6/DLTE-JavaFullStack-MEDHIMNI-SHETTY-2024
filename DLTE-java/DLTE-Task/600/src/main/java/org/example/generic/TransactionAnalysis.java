package org.example.generic;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionAnalysis {
    public static void main(String[] args ){
        System.out.println("-------------Welcome to our Bank-------------");
        Integer start,end;
        String sentTo=" ",remarks=" ";
        Scanner scanner=new Scanner(System.in);
        start=scanner.nextInt();
        end=scanner.nextInt();
        List<Transaction> transaction=new ArrayList<>();
        transaction.add(new Transaction(new Date(2024,01,01),4000.67,"Megha","family"));
        transaction.add(new Transaction(new Date(2024,02,19),498.0,"Mothi","medical"));
        transaction.add(new Transaction(new Date(2024,01,03),6700.0,"Munni","education"));
        transaction.add(new Transaction(new Date(2024,03,30),900.0,"Athul","emergency"));
        transaction.add(new Transaction(new Date(2024,01,29),7846.0,"Pramodha","Family"));
        transaction.add(new Transaction(new Date(2024,01,15),4056.0,"Prajothi","education"));
        transaction.add(new Transaction(new Date(2024,02,04),600.0,"Prathima","Family"));
        TransactionAnalysis transactionAnalysis=new TransactionAnalysis();
        System.out.println("Enter your choice");
        System.out.println("1.Filter based on given ranges of date\n" +
                "2.least amount transferred \n" +
                "3.maximum amount transferred\n" +
                "4.Customized sort based on property");
        int choice=0;
        choice=scanner.nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter start date: "+start);
                System.out.println("Enter end date: "+end);
                transactionAnalysis.FilterBasedOnRangeOfDate(transaction,start,end);
                break;
            case 2:transactionAnalysis.LeastAmountTransferred(transaction);
                   break;
            case 3:transactionAnalysis.MaximumAmountTransferred(transaction);
                   break;
            case 4:
                System.out.println("Enter the property(date,amount,to,remarks)");
                String property=scanner.next();
                System.out.println("Enter the sorting order(ascending or descending)");
                String sortingOrder=scanner.next();
                Comparator<Transaction> comparator=null;
                switch (property.toLowerCase()){
                    case "date":comparator=Comparator.comparing(Transaction::getDateoftransaction);
                    break;
                    case "amount":comparator=Comparator.comparing(Transaction::getTransactionamount);
                    break;
                    case "to":comparator=Comparator.comparing(Transaction::getTo);
                    break;
                    case "remark":comparator=Comparator.comparing(Transaction::getRemarks);
                    break;
                    default:
                        System.out.println("Invalid entry");
                        break;
                }
                if(sortingOrder.equalsIgnoreCase("desc")) comparator=comparator.reversed();
                transactionAnalysis.transactionSort(transaction,comparator);

        }
        scanner.close();
    }
    public void FilterBasedOnRangeOfDate(List<Transaction> Data,Integer start,Integer end){
        System.out.println("Displaying the details of transaction between "+start+" and "+end);
        List<Transaction> transactions=Data.stream().filter(transaction ->transaction.getDateoftransaction().getTime()>=start && transaction.getDateoftransaction().getDate()<=end).collect(Collectors.toList());
        transactions.forEach(transaction -> System.out.println(transaction.getTo()+" "+transaction.getTransactionamount()));
    }
    public void LeastAmountTransferred(List<Transaction> Data){
        Transaction transactions=Data.stream().min(Comparator.comparing(Transaction::getTransactionamount)).orElse(null);
        System.out.println("least amount transfered to "+transactions.getTo());
    }
    public void MaximumAmountTransferred(List<Transaction> Data){
        Transaction transactions=Data.stream().max(Comparator.comparing(Transaction::getTransactionamount)).orElse(null);
        System.out.println("Maximum amount transfered to "+transactions.getTo());
    }
    public void transactionSort(List<Transaction> Data,Comparator<Transaction> comparator){
        List<Transaction> transactions=Data.stream().sorted(comparator).collect(Collectors.toList());
        System.out.println(transactions);
    }
}
