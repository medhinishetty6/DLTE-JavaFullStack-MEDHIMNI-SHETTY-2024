package First;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/First/*")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mapping = req.getPathInfo();
        String investment = req.getParameter("investment");
        String period = req.getParameter("period");
        String estimatedAmount = req.getParameter("estimatedAmount");
        String annualIncome = req.getParameter("annualIncome");
        //         long invt;
//        int years;
//        double rate = 0,TAmount=0;
//        System.out.println("Enter investment per mount");
//        invt=sc.nextLong();
//        System.out.println("Enter the time duration");
//        years=sc.nextInt();
//        System.out.println("Enter the intrest rate");
//        rate=rate/(12*100);
//        years *=12;
//        TAmount = (invt*((Math.pow(1+rate,years)-1)/rate)*(1+rate));
//        System.out.println("the output is"+TAmount);
//        }
        if (investment != null && estimatedAmount != null && period != null) {
            double perMonthInvestment = Double.parseDouble(investment);
            double estimatedReturn = Double.parseDouble(estimatedAmount);
            double perMonthIntrestRate = estimatedReturn / 12 / 100;
            int totalPeriod = Integer.parseInt(period);
            double totalMonths = 12 * totalPeriod;
            double totalReturn = perMonthInvestment * ((Math.pow((1 + perMonthIntrestRate), (totalMonths)) - 1) * (1 + perMonthIntrestRate)) / perMonthIntrestRate;
            double totalmoney = totalMonths * perMonthInvestment;
            double expectedReturn = totalReturn - totalmoney;
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Expected amount= " + expectedReturn + "Total return= " + totalReturn + "Total investment= " + totalmoney);
        } else {
            String regime = req.getParameter("regime");
            String received = findTax(Double.parseDouble(annualIncome), regime);
            resp.getWriter().println("Received");
        }

    }

    private String findTax(double Income, String regime) {
        double tax = 0;
        switch (regime) {
            case "o": {
                if (Income >= 0 && Income <= 250000) {
                    tax = 0;
                    System.out.println("Tax payed" + tax);
                } else if (Income >= 250000 && Income <= 550000) {
                    tax = Income * 0.05;
                    System.out.println("Tax payed" + tax);
                } else if (Income >= 550000 && Income <= 750000) {
                    tax = Income * 0.20;
                    System.out.println("Tax payed" + tax);
                } else if (Income >= 750000 && Income <= 1000000) {
                    tax = Income * 0.20;
                    System.out.println("Tax payed" + tax);
                } else if (Income >= 1000000 && Income <= 1250000) {
                    tax = Income * 0.30;
                    System.out.println("Tax payed" + tax);
                } else if (Income >= 1250000 && Income <= 1500000) {
                    tax = Income * 0.30;
                    System.out.println("Tax payed" + tax);
                } else {
                    tax = Income * 0.30;
                    System.out.println("Tax payed" + tax);
                }
                break;
            }
            case "": {
                if (Income >= 0 && Income <= 250000) {
                    tax = 0;
                    System.out.println("Tax payed" + tax);
                } else if (Income >= 250000 && Income <= 500000) {
                    tax = Income * 0.05;
                    System.out.println("Tax payed" + tax);
                } else if (Income >= 500000 && Income <= 750000) {
                    tax = Income * 0.10;
                    System.out.println("Tax payed" + tax);
                } else if (Income >= 750000 && Income <= 1000000) {
                    tax = Income * 0.15;
                    System.out.println("Tax payed" + tax);
                } else if (Income >= 1000000 && Income <= 1250000) {
                    tax = Income * 0.20;
                    System.out.println("Tax payed" + tax);
                } else if (Income >= 1250000 && Income <= 1500000) {
                    tax = Income * 0.25;
                    System.out.println("Tax payed" + tax);
                } else {
                    tax = Income * 0.30;
                    System.out.println("Tax payed" + tax);
                }
                break;
            }
        }

        return regime;
    }
}



