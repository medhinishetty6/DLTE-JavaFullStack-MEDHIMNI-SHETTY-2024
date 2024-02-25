package basics.loanProduct;
import java.util.Date;

public class Loan {
    private String Loan_Date;
    private String Loan_Status;
    private String Borrower_Name;
    private Long Loan_Number;
    private Long Loan_Amount;
    private Long Borrower_Contact;

    public Loan(String loan_Date, String loan_Status, String borrower_Name, Long loan_Number, Long loan_Amount, Long borrower_Contact) {
        this.Loan_Date = Loan_Date;
        this.Loan_Status = Loan_Status;
        this.Borrower_Name = Borrower_Name;
        this.Loan_Number = Loan_Number;
        this.Loan_Amount = Loan_Amount;
        this.Borrower_Contact = Borrower_Contact;
    }

    public String getLoan_Date() {
        return Loan_Date;
    }

    public void setLoan_Date(String loan_Date) {
        Loan_Date = loan_Date;
    }

    public String getLoan_Status() {
        return Loan_Status;
    }

    public void setLoan_Status(String loan_Status) {
        Loan_Status = loan_Status;
    }

    public String getBorrower_Name() {
        return Borrower_Name;
    }

    public void setBorrower_Name(String borrower_Name) {
        Borrower_Name = borrower_Name;
    }

    public Long getLoan_Number() {
        return Loan_Number;
    }

    public void setLoan_Number(Long loan_Number) {
        Loan_Number = loan_Number;
    }

    public Long getLoan_Amount() {
        return Loan_Amount;
    }

    public void setLoan_Amount(Long loan_Amount) {
        Loan_Amount = loan_Amount;
    }

    public Long getBorrower_Contact() {
        return Borrower_Contact;
    }

    public void setBorrower_Contact(Long borrower_Contact) {
        Borrower_Contact = borrower_Contact;
    }
}
