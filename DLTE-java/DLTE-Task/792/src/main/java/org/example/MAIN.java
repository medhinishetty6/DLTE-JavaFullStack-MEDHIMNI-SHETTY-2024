package org.example;

import java.beans.beancontext.BeanContext;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.example.MyBank.loan;

public class MAIN implements MyBank1{
    ArrayList<Loan> loans = loan;

    @Override
    public void write() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("LoanData.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(loans);
        fileOutputStream.close();
        objectOutputStream.close();
    }
    @Override
    public void read() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("CreditCard");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        loans = (ArrayList<Loan>) objectInputStream.readObject();
        int size = loans.size();
        for (int index = 0; index < size; index++) {
            if (loans.get(index) != null) {
                System.out.println(loans.get(index).toString());

            }
        }
    }
    public void addNewLoan(Loan loan) {
        try {
            read();
        }
        catch (IOException|ClassNotFoundException E){

            System.out.println(E);
        }
        loans.add(loan);

        try {
            write();
        }
        catch (IOException E){

            System.out.println(E);
        }

    }

    @Override
    public void Availability(){
    try{
           read();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
        assert stream() != null;
        List<Loan> available=stream().filter(each-> {
            return each.getLoanStatus().equals("Open");
        }).collect(Collectors.toList());
    available.forEach(Loan->{
        System.out.println(Loan.toString());
    });
    }

    private Locale stream() {
        return null;
    }
    @Override
    public void closedLoan() {
        try {
            read();
        }
        catch (IOException|ClassNotFoundException E){

            System.out.println(E);
        }
        List<Loan> notAvailable=loans.stream().filter(each->each.getLoanStatus().equals("Close")).collect(Collectors.toList());
        notAvailable.forEach(Loan->{
            System.out.println(Loan.toString());
        });
    }
    public Loan InputData(){
        Scanner scanner=new Scanner(System.in);
        long loanNumber;
        System.out.println("Enter the loan number");
        loanNumber=scanner.nextLong();
        double loanAmount;
        System.out.println("Enter the loan amount");
        loanAmount= scanner.nextDouble();
        Date loanDate=new Date();
        String loanStatus;
        System.out.println("Loan status");
        loanStatus=scanner.next();
        String borrowerName;
        System.out.println("Enter the borrowers name");
        borrowerName=scanner.next();
        Long borrowerContact;
        System.out.println("Enter the borrowers contact detail");
        borrowerContact=scanner.nextLong();
        return new Loan(loanNumber,loanAmount,loanDate,loanStatus,borrowerName,borrowerContact);
    }
    public static void main(String[] args){
        MAIN loanTest=new MAIN();
        Loan loan1=new Loan(895648123L,900.00,new Date(2024,01,29),"Open","Medhini",8956231452L);
        Loan loan2=new Loan(123587452L,9900.00,new Date(2024,01,19),"Closed","Meghana",5566332211L);
        Loan loan3=new Loan(963452661L,800.00,new Date(2024,03,07),"Open","Madan",8452156522L);
        Loan loan4=new Loan(632876509L,400.00,new Date(2024,02,27),"Open","Prajothi",8958908776L);
        try{
            loanTest.write();
        }catch (IOException E){
            System.out.println(E);
        }
        int choice=0;
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("----Welcome to our bank---");
            System.out.println("Enter your choice");
            System.out.println("1.add new loan \n" +
                    "2.check available loans\n" +
                    "3.check only closed loans");
            choice=scanner.nextInt();
            switch (choice){
                case 1:loanTest.addNewLoan(loanTest.InputData());
                break;
                case 2:loanTest.Availability();
                break;
                case 3:loanTest.closedLoan();
            }


        }
    }
    }


