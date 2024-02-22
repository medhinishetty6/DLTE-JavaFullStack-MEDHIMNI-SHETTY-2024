package branch.blocks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class creditcard {
    private Long creditCardNo;
    private String  creditCardHolder;
    private Date creditCardExpiry;
    private  Integer creditCardCvv;
    private  Integer CreditCardLimit;
    private Date BillGenerationDate;
    private  Integer BillPaymentDate;
    private  Integer Pin;

    @Override
    public String toString() {
        return "CreditCardAnalysis{" +
                "creditCardNo=" + creditCardNo +
                ", creditCardHolder='" + creditCardHolder + '\'' +
                ", creditCardExpiry=" + creditCardExpiry +
                ", creditCardCvv=" + creditCardCvv +
                ", CreditCardLimit=" + CreditCardLimit +
                ", BillGenerationDate=" + BillGenerationDate +
                ", BillPaymentDate=" + BillPaymentDate +
                ", Pin=" + Pin +
                '}';
    }

    public Long getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(Long creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getCreditCardHolder() {
        return creditCardHolder;
    }

    public void setCreditCardHolder(String creditCardHolder) {
        this.creditCardHolder = creditCardHolder;
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
        return CreditCardLimit;
    }

    public void setCreditCardLimit(Integer creditCardLimit) {
        CreditCardLimit = creditCardLimit;
    }

    public Date getBillGenerationDate() {
        return BillGenerationDate;
    }

    public void setBillGenerationDate(Date billGenerationDate) {
        BillGenerationDate = billGenerationDate;
    }

    public Integer getBillPaymentDate() {
        return BillPaymentDate;
    }

    public void setBillPaymentDate(Integer billPaymentDate) {
        BillPaymentDate = billPaymentDate;
    }

    public Integer getPin() {
        return Pin;
    }

    public void setPin(Integer pin) {
        Pin = pin;
    }
}

