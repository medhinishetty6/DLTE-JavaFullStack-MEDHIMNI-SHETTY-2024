package org.example;
import java.util.*;
import java.util.Date;

public class AnalysisTransaction {
    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager();
        //Inserting new transactions
        try {
            manager.insertTransaction(new Date(2024,01,21), 100.0, "Medh", "family");
            manager.insertTransaction(new Date(2024,02,12), 200.0, "peh", "Education");
            manager.insertTransaction(new Date(2024,01,05), 300.0, "poh", "Emergency"); //this will thro an exseption
            manager.insertTransaction(new Date(2024,03,01), 400.0, "bob", "Education");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception occured: " + e.getMessage());
        }

        //print all transactions
        System.out.println("All Transactions:");
        for (Transaction transaction : manager.transactions) {
            System.out.println(transaction);
        }
        //Delete transaction of given to
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the transactcion details to delete ");
        to = scanner.nextLine();
        manager.deletTransaction(to);

        //filter transaction for Education
        ArrayList<Transaction> educationTransactions = manager.filterTransactionsforEducation();
        System.out.println("\nEducation Transaction: ");
        for(Transaction transaction :educationTransactions){
            System.out.println(transaction);
        }

    }
}

