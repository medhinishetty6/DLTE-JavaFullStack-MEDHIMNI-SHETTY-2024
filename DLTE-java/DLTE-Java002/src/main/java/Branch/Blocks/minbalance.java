package Branch.Blocks;
import java.util.*;

public class minbalance {
    public static void main(String[] args) {
        int[] customerbal = new int[20];
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<20;i++)
        {
            System.out.println("Enter"+(i+1)+"balance");
            customerbal[i]=scanner.nextInt();
        }
        updatebal(customerbal);
        System.out.println("Updated balance is");
        System.out.println(Arrays.toString(customerbal));
    }
    public static void updatebal ( int[] bal){
        for (int j = 0; j< 20; j++) {
            int presentbal = bal[j];
            if (presentbal >= 5000 && presentbal < 10000) {
                bal[j] -= presentbal * 0.03;
            } else if (presentbal >= 1000 && presentbal < 5000) {
                bal[j] -= presentbal * 0.05;
            }

        }
    }
}
