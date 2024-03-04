package org.example;

import com.sun.jndi.cosnaming.IiopUrl;

import java.util.Scanner;

public class Employee {

    private String firstName;
    private String middleName;
    private String lastName;
    private Long phoneNo;
    private String employeeID;
    private String emailID;
    private EmployeeAddress permanentAddress;
    private EmployeeAddress temporaryAddress;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String  getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public EmployeeAddress getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(EmployeeAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public EmployeeAddress getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(EmployeeAddress temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public Employee(String firstName, String middleName, String lastName, Long phoneNo, String employeeID, String emailID, EmployeeAddress permanentAddress, EmployeeAddress temporaryAddress) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.employeeID = employeeID;
        this.emailID = emailID;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
    }
    public  void displayDetails(){
        String pincode = null;
        String localityName = null;
        String areaName = null;
        String houseName = null;
        EmployeeAddress employeeAddress=new EmployeeAddress(houseName,areaName,localityName,pincode);
        System.out.println("Employee Name: "+firstName+" "+middleName+" "+lastName);
        System.out.println("Phone No: "+phoneNo);
        System.out.println("Employee ID: "+employeeID);
        System.out.println("Email ID: "+emailID);
        System.out.println("Permanent Address: ");
        employeeAddress.displayAddress();
        System.out.println("Temporary Address: ");
        employeeAddress.displayAddress();

    }
}