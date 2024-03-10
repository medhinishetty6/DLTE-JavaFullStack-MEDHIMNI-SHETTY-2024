package org.example;

import java.io.Serializable;
import java.util.Date;

public class CreditCard implements Serializable {
    private String CardHolder;
    private Long CardNumber;
    private Integer CardCVV;
    private Date CardExpiry;
    private Integer CardLimit;
    private Integer CardPIN;
    private Date BillGenerationDate;
    private Date BillPaymentDate;

    @Override
    public String toString() {
        return "CreditCard{" +
                "CardHolder='" + CardHolder + '\'' +
                ", CardNumber=" + CardNumber +
                ", CardCVV=" + CardCVV +
                ", CardExpiry=" + CardExpiry +
                ", CardLimit=" + CardLimit +
                ", CardPIN=" + CardPIN +
                ", BillGenerationDate=" + BillGenerationDate +
                ", BillPaymentDate=" + BillPaymentDate +
                '}';
    }

    public CreditCard(String cardHolder, Long cardNumber, Integer cardCVV, Date cardExpiry, Integer cardLimit, Integer cardPIN, Date billGenerationDate, Date billPaymentDate) {
        CardHolder = cardHolder;
        CardNumber = cardNumber;
        CardCVV = cardCVV;
        CardExpiry = cardExpiry;
        CardLimit = cardLimit;
        CardPIN = cardPIN;
        BillGenerationDate = billGenerationDate;
        BillPaymentDate = billPaymentDate;
    }

    public String getCardHolder() {
        return CardHolder;
    }

    public void setCardHolder(String cardHolder) {
        CardHolder = cardHolder;
    }

    public Long getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        CardNumber = cardNumber;
    }

    public Integer getCardCVV() {
        return CardCVV;
    }

    public void setCardCVV(Integer cardCVV) {
        CardCVV = cardCVV;
    }

    public Date getCardExpiry() {
        return CardExpiry;
    }

    public void setCardExpiry(Date cardExpiry) {
        CardExpiry = cardExpiry;
    }

    public Integer getCardLimit() {
        return CardLimit;
    }

    public void setCardLimit(Integer cardLimit) {
        CardLimit = cardLimit;
    }

    public Integer getCardPIN() {
        return CardPIN;
    }

    public void setCardPIN(Integer cardPIN) {
        CardPIN = cardPIN;
    }

    public Date getBillGenerationDate() {
        return BillGenerationDate;
    }

    public void setBillGenerationDate(Date billGenerationDate) {
        BillGenerationDate = billGenerationDate;
    }

    public Date getBillPaymentDate() {
        return BillPaymentDate;
    }

    public void setBillPaymentDate(Date billPaymentDate) {
        BillPaymentDate = billPaymentDate;
    }
}

