package org.example;

import java.util.Date;

public class UserFriendlyCreditCard {
    // Details of Credit Card
    private Long creditCardNumber;
    private String creditCardHOlder;
    private Date creditCardExpiry;
    private Integer creditCardCvv;
    private Integer creditCardLimit;
    private Date dateOfBillGeneration;
    private Date dateOfBillPayment;
    private Integer creditCardPin;

    public UserFriendlyCreditCard(Long creditCardNumber, String creditCardHOlder, Date creditCardExpiry, Integer creditCardCvv, Integer creditCardLimit, Date dateOfBillGeneration, Date dateOfBillPayment, Integer creditCardPin) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardHOlder = creditCardHOlder;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardCvv = creditCardCvv;
        this.creditCardLimit = creditCardLimit;
        this.dateOfBillGeneration = dateOfBillGeneration;
        this.dateOfBillPayment = dateOfBillPayment;
        this.creditCardPin = creditCardPin;
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardHOlder() {
        return creditCardHOlder;
    }

    public void setCreditCardHOlder(String creditCardHOlder) {
        this.creditCardHOlder = creditCardHOlder;
    }

    public Date getCreditCardExpiry() {
        return creditCardExpiry;
    }

    public void setCreditCardExpiry(Date creditCardExpiry) {
        this.creditCardExpiry = creditCardExpiry;
    }

    public Integer getCreditCardCvv() {
        return creditCardCvv;
    }

    public void setCreditCardCvv(Integer creditCardCvv) {
        this.creditCardCvv = creditCardCvv;
    }

    public Integer getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(Integer creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public Date getDateOfBillGeneration() {
        return dateOfBillGeneration;
    }

    public void setDateOfBillGeneration(Date dateOfBillGeneration) {
        this.dateOfBillGeneration = dateOfBillGeneration;
    }

    public Date getDateOfBillPayment() {
        return dateOfBillPayment;
    }

    public void setDateOfBillPayment(Date dateOfBillPayment) {
        this.dateOfBillPayment = dateOfBillPayment;
    }

    public Integer getCreditCardPin() {
        return creditCardPin;
    }

    public void setCreditCardPin(Integer creditCardPin) {
        this.creditCardPin = creditCardPin;
    }
}
