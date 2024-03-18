package org.example;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class FilterTransactionByUser {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter user name to filter transactions: ");
            String userToFilter = scanner.nextLine();

            File file = new File("transactions.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(CreateTransaction.TransactionsWrapper.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CreateTransaction.TransactionsWrapper transactionsWrapper = (CreateTransaction.TransactionsWrapper) jaxbUnmarshaller.unmarshal(file);

            List<Transaction> transactions = transactionsWrapper.getTransactions();
            for (Transaction transaction : transactions) {
                if (transaction.getUser().equals(userToFilter)) {
                    System.out.println("User: " + transaction.getUser());
                    System.out.println("To"+transaction.getTo());
                    System.out.println("Date: " + transaction.getDate());
                    System.out.println("Amount: " + transaction.getAmount());
                    System.out.println();
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
