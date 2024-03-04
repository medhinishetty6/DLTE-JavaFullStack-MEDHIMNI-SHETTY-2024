package org.example;

import java.util.Scanner;

public class EmployeeDetails{
    public static void main(String[] args ){

        Scanner scanner=new Scanner(System.in);
        System.out.println("-----Welcome to my Bank-------");
        System.out.println("Enter your name");
        System.out.println("First Name: ");
        String firstName=scanner.nextLine();
        System.out.println("Middle Name: ");
        String middleName=scanner.nextLine();
        System.out.println("Last Name: ");
        String lastName=scanner.nextLine();
        System.out.println("Phone No: ");
        Long phoneNo=scanner.nextLong();
        System.out.println("Employee ID: ");
        String employeeID=scanner.nextLine();
        System.out.println("Email ID");
        String emailID=scanner.nextLine();

        System.out.println("Enter Permanent Address Details: ");
        String permanentAddress=getAddressDetails(scanner);

        System.out.println("Enter Temporary Address Details: ");
        String temporaryAddress=getAddressDetails(scanner);

        Employee employee=new Employee(firstName,middleName,lastName,phoneNo, employeeID, emailID, permanentAddress,temporaryAddress);
        System.out.println("\nEmployee Details: ");
        employee.displayDetails();
    }
    private  static  String getAddressDetails(Scanner scanner){
        System.out.println("House Name: ");
        String houseName=scanner.nextLine();
        System.out.println("area Name: ");
        String areaName=scanner.nextLine();
        System.out.println("Locality Name: ");
        String localityName=scanner.nextLine();
        System.out.println("Pincode : ");
        String pincode=scanner.nextLine();
        return  houseName+" "+areaName+" "+localityName+" "+pincode;
    }
}

