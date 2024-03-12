package Rest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
    private List<Transaction> transactions=new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String amountStr=req.getParameter("amount");
        double amount=Double.parseDouble(amountStr);
        Transaction transaction=new Transaction(amount);
        transaction.add(transaction);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String minStr=req.getParameter("min");
        String maxStr=req.getParameter("max");

        if(minStr!=null && maxStr!=null){
            double min = Double.parseDouble(minStr);
            double max = Double.parseDouble(maxStr);
            List<Transaction> filteredTransactions=new ArrayList<>();
            for(Transaction transaction:transactions){
                if(transaction.getTransactionamount()>=min&&transaction.getTransactionamount()<=max){
                    filteredTransactions.add(transaction);
                }
            }
            resp.setContentType("application/json");
            PrintWriter out=resp.getWriter();
            out.println(filteredTransactions);
        }else {
            resp.setContentType("application/json");
            PrintWriter out=resp.getWriter();
            out.println(transactions);
        }
    }


}
