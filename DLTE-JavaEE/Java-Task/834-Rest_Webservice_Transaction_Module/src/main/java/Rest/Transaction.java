package Rest;

import java.util.Date;

public class Transaction {
    private Date dateoftransaction;
    private  Double amount;
    private  String  to;
    private  String remarks=" ";

    public Transaction(double amount) {
    }



    public Date getDateoftransaction() {
        return dateoftransaction;
    }

    public void setDateoftransaction(Date dateoftransaction) {
        this.dateoftransaction = dateoftransaction;
    }

    public Double getTransactionamount() {
        return amount;
    }

    public void setTransactionamount(Double transactionamount) {
        this.amount = transactionamount;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "dateoftransaction=" + dateoftransaction +
                ", transactionamount=" + amount +
                ", to='" + to + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public Transaction(Date dateoftransaction, Double transactionamount, String transactionto, String remarks) {
        this.dateoftransaction = dateoftransaction;
        this.amount = transactionamount;
        this.to = to;
        this.remarks = remarks;
    }

    public void add(Transaction transaction) {

    }
}
