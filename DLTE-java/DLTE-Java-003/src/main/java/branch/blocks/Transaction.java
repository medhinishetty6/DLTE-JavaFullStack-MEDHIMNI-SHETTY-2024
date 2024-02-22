package branch.blocks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Arrays;
import java.util.Date;
@Data

public class Transaction
{
    public  Transaction(){
        System.out.println("Initialize the transaction properties manually ");
    }


    private Double transactionamount;
    private  String  transactionto;
    private  Date dateoftransaction;
    private  String remarks=" ";

    public Transaction(Double transactionamount, String transactionto, Date dateoftransaction, String remarks) {
        this.transactionamount = transactionamount;
        this.transactionto = transactionto;
        this.dateoftransaction = dateoftransaction;
        this.remarks = remarks;
    }

    public Double getTransactionamount() {
        return transactionamount;
    }

    public void setTransactionamount(Double transactionamount) {
        this.transactionamount = transactionamount;
    }

    public String getTransactionto() {
        return transactionto;
    }

    public void setTransactionto(String transactionto) {
        this.transactionto = transactionto;
    }

    public Date getDateoftransaction() {
        return dateoftransaction;
    }

    public void setDateoftransaction(Date dateoftransaction) {
        this.dateoftransaction = dateoftransaction;
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
                "transactionamount=" + transactionamount +
                ", transactionto='" + transactionto + '\'' +
                ", dateoftransaction=" + dateoftransaction +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}

