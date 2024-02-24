package explore.oop.executions;
import java.util.Scanner;

public class gpay extends accountsDetail{
    String UserName;
    Integer UPIpin;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Integer getUPIpin() {
        return UPIpin;
    }

    public void setUPIpin(Integer UPIpin) {
        this.UPIpin = UPIpin;
    }

    public gpay(Long accountNumber, Long accountBalance, String accountHolder, String userName, Integer UPIpin) {
        super(accountNumber, accountBalance, accountHolder);
        UserName = userName;
        this.UPIpin = UPIpin;
    }
    public void BillPayment(String billType, String  billername,Long billAmount) {
        int enterPIN = 0;
        System.out.println("Enmter the UPI pin");
        Scanner scanner = new Scanner(System.in);
        enterPIN = scanner.nextInt();
        if (enterPIN == getUPIpin()) {
            if (billAmount <= getAccountBalance()) {
                System.out.println("Bill payed \n" + "Biller name : " + billername + " \nBilled amount : " + billAmount + "\nBill type : " + billType);
                setAccountBalance(getAccountBalance() - billAmount);
            } else {
                System.out.println("Incorrect password");
            }
            System.out.println(getAccountBalance());
        }

    }
}
