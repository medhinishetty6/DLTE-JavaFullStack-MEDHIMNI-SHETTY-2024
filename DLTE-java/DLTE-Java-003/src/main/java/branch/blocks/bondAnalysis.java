package branch.blocks;

public class bondAnalysis {
    public static void main(String[] args) {
        Bonds bondArray[] = {
                new Bonds(6000, 8.0, "Payable", "Prajothi", 3),
                new Bonds(5000, 9.0, "not Payable", "Madhan", 5),
                new Bonds(7000, 10.0, "Payable", "Meghana", 2),
                new Bonds(8000, 7.0, "Payable", "Bab", 4)
        };
        bondAnalysis bond = new bondAnalysis();
        bond.sort(bondArray);
    }

    public void sort(Bonds[] bondarray) {
        System.out.println("Amount before sorting");
        for (Bonds each : bondarray) {
            System.out.println(each.getInterestrate());
        }
        for (int select = 0; select < bondarray.length; select++) {
            for (int next = 0; next < bondarray.length - select - 1; next++) {
                if (bondarray[next].getInterestrate().compareTo(bondarray[next +1].getInterestrate()) < 0) {
                    Bonds backup = bondarray[next];
                    bondarray[next] = bondarray[next + 1];
                    bondarray[next + 1] = backup;
                }
            }
        }
        System.out.println("After sorting");
        for (Bonds each : bondarray) {
            System.out.println(each.getInterestrate());
        }
    }

}
