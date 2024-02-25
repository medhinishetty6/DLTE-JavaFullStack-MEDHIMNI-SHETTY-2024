package basics.loanProduct;

public interface Bank {
    Loan[] loan=new Loan[5];

    void Add_New_Loan();
    void Check_Available_Loans();
    void Check_Closed_Loans();
}
