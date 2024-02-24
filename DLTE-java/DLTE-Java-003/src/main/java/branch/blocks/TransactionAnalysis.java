package branch.blocks;
import  java.util.Date;
import java.util.Scanner;
public class TransactionAnalysis {
    public static void main(String[] args) {

        Transaction[] myBank = {
                new Transaction(587463.52, "Raju", new Date(2024, 7, 28), "family"),
                new Transaction(46553.02, "Savita", new Date(2022, 2, 2), "emergency"),
                new Transaction(639125.15, "Prem", new Date(2024, 7, 20), "bill"),
                new Transaction(968523.26, "Riya", new Date(2024, 7, 25), "education"),
                new Transaction(96355.25, "Tony", new Date(2024, 6, 28), "family"),
        };
        Scanner scanner = new Scanner(System.in);
        TransactionAnalysis analysis = new TransactionAnalysis();
        analysis.rangeDate(myBank, new Date(2024, 2, 5), new Date(2024, 12, 29));
        ;
        analysis.leastAmount(myBank);
        analysis.highestAmount(myBank);
        analysis.noOftxnOnbeneficiary(myBank, "education");
        analysis.noOftxnOnbeneficiary(myBank, "emergency");
        analysis.Filterbasedonremark(myBank, "family");
        System.out.println("Before sorting");
        analysis.printvalue(myBank);
        analysis.benificiarydecending(myBank);
        System.out.println("After sorting");
        analysis.printvalue(myBank);
        System.out.println("Before sorting");
        analysis.printvalue(myBank);
        analysis.amountacending(myBank);
        System.out.println("After sorting");
        analysis.printvalue(myBank);
    }

    public void rangeDate(Transaction[] array, Date start, Date end) {
        System.out.println("Transaction made between" + start.getDate() + " and " + end.getDate());
        for (Transaction each : array) {
            if (each.getDateoftransaction().getDate() >= start.getDate() && each.getDateoftransaction().getDate() <= end.getDate()) {
                System.out.println("The amount of Rs " + each.getTransactionamount() + " was transfered to" + each.getTransactionto() + " for " + each.getRemarks() + " no " + each.getDateoftransaction());

            }
        }
    }

    public void leastAmount(Transaction[] customer) {
        Transaction backup = null;
        for (int select = 0; select < customer.length; select++) {
            for (int next = select + 1; next < customer.length; next++) {
                if (customer[select].getTransactionamount().compareTo(customer[next].getTransactionamount()) > 0) {
                    backup = customer[select];
                    customer[select] = customer[next];
                    customer[next] = backup;
                }
            }
        }
        System.out.println("least amount transfered is " + customer[0].getTransactionamount());

    }

    public void highestAmount(Transaction[] customer) {
        Transaction backup = null;
        for (int select = 0; select < customer.length; select++) {
            for (int next = select + 1; next < customer.length; next++) {
                if (customer[select].getTransactionamount().compareTo(customer[next].getTransactionamount()) > 0) {
                    backup = customer[select];
                    customer[select] = customer[next];
                    customer[next] = backup;
                }
            }
        }
        System.out.println(" Maximum amount transferred is " + customer[customer.length - 1].getTransactionamount());
    }

    public void noOftxnOnbeneficiary(Transaction[] array, String beneficiary) {
        int count_no_of_transaction = 0;
        for (Transaction each : array) {
            if (each.getRemarks().equals(beneficiary)) {
                count_no_of_transaction += 1;
            }
        }
        System.out.println("Based on " + beneficiary + " " + count_no_of_transaction + " transactions are performed");
    }

    public void Filterbasedonremark(Transaction[] array, String beneficiary) {
        for (Transaction each : array) {
            if (each.getRemarks().equals(beneficiary)) {
                System.out.println("On" + each.getRemarks() + " beneficiary the following are made");
                for (Transaction each1 : array) {
                    if (each1.getRemarks().equals(beneficiary))
                        System.out.println("Transaction amount of " + each1.getTransactionamount() + " is trancefered to " + each1.getTransactionto() + "on " + each1.getDateoftransaction());
                }
            }
            break;
        }
    }



    public void printvalue(Transaction[] array) {
        for (Transaction each : array) {
            System.out.println(each);
        }
    }

    public void benificiarydecending(Transaction[] array) {
        Transaction backup = null;
        for (int select = 0; select < array.length; select++) {
            for (int next = 0; next < array.length - next - 1; next++) {
                if (array[next + 1].getRemarks().compareTo(array[next].getRemarks()) > 0) {
                    backup = array[next];
                    array[next] = array[next + 1];
                    array[next + 1] = backup;
                }
            }
        }
    }

    public void amountacending(Transaction[] array) {
        Transaction backup = null;
        for (int select = 0; select < array.length; select++) {
            for (int next = 0; next < array.length - next - 1; next++) {
                if (array[next + 1].getTransactionamount().compareTo(array[next].getTransactionamount()) > 0) {
                    backup = array[next];
                    array[next] = array[next + 1];
                    array[next + 1] = backup;
                }
            }
        }
    }
}


