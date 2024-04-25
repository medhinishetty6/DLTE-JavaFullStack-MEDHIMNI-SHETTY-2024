package crud.loans;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class Loans {
    private Long loanNumber;
    private Double loanAmount;
    private String loanDate;
    private String loanStatus;
    private String borrowerName;
    private Long borrowerContact;
    private List<Loans> loanList;

    @Override
    public String toString() {
        return "Loans{" +
                "loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", loanDate='" + loanDate + '\'' +
                ", loanStatus='" + loanStatus + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerContact=" + borrowerContact +
                ", loanList=" + loanList +
                '}';
    }

    public Loans(Long loanNumber, Double loanAmount, String loanDate, String loanStatus, String borrowerName, Long borrowerContact) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanStatus = loanStatus;
        this.borrowerName = borrowerName;
        this.borrowerContact = borrowerContact;
        this.loanList = loanList;
    }

    public Long getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getBorrowerContact() {
        return borrowerContact;
    }

    public void setBorrowerContact(Long borrowerContact) {
        this.borrowerContact = borrowerContact;
    }

    public List<Loans> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loans> loanList) {
        this.loanList = loanList;
    }

    public void addLoan(Loans loan) {
        loanList.add(loan);
    }

    @PostConstruct
    public void initLoans() {
        loanList = new ArrayList<>();
        loanList.add(new Loans(111L, 8000.0, "02/02/2024", "open", "Medhini", 8147533826L));
        loanList.add(new Loans(112L, 90000.0, "14/01/2024", "open", "Meghana", 2233669955L));
        loanList.add(new Loans(113L, 78000.0, "10/01/2024", "closed", "Madan", 4856321589L));
        loanList.add(new Loans(114L, 50000.0, "24/03/2024", "closed", "Mighty", 4128566932L));
    }

    public List<Loans> getClosedLoans() {
        List<Loans> displayclosedLoans = new ArrayList<>();
        for (Loans loan : loanList) {
            if (loan.getLoanStatus().equalsIgnoreCase("closed")) {
                displayclosedLoans.add(loan);
            }
        }
        return displayclosedLoans;
    }

    public void deleteLoan(Long loanNumber) {
        loanList.removeIf(loan -> loan.getLoanNumber().equals(loanNumber));
    }

    public String allLoans(){
        return loanList.toString();
    }
}
