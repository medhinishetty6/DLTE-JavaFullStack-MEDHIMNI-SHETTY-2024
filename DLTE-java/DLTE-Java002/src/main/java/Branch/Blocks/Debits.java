package Branch.Blocks;
import java.util.Scanner;
public class Debits {
    public  static void main(String [] args) {
        int transactions;
        String dep, cre, dep1;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i <= 10; i++) {
            System.out.println("Enter the transaction history");
            dep = scanner.nextLine();
            cre = scanner.nextLine();
            System.out.printf("Enter the debited amount=" + dep + "");
            System.out.printf("Enter the Credited amount=" + cre + "");
            dep1 = dep + 1;
            System.out.println("Total number of debits=" + dep1 + "");
        }
    }
}
