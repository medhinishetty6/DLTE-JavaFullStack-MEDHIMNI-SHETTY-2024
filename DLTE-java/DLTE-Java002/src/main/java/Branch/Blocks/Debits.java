package Branch.Blocks;
import java.util.*;
public class Debits {
    public static void main(String[] args) {
        int i = 10, n = 1, debit = 0;
        double presentBal, TBal, transactions;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Total Balance");
        TBal = sc.nextDouble();
        while(i != 0) {
             i-= 1;
            System.out.println("Enter the updated balance amount" + n);
            n += 1;
            presentBal = sc.nextDouble();
            if (presentBal < TBal) {
                debit += 1;
                TBal = presentBal;
            }
            TBal = presentBal;
        }
        System.out.println("The no of debit transaction is" + debit);
    }
}
