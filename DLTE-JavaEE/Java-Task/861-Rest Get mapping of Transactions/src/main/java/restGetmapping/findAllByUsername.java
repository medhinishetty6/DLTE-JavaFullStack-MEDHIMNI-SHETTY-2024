package restGetmapping;

import com.google.gson.Gson;
import org.example.entity.Transaction;
import org.example.exceptions.TransactionNotFoundException;
import org.example.middleware.DatabaseTarget;
import org.example.remote.StorageTarget;
import org.example.services.TransactionServices;
import org.omg.IOP.TransactionService;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class findAllByUsername extends HttpServlet {
    TransactionServices service;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        service= new TransactionServices(storageTarget);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        String sender=req.getParameter("sender");
        resp.setContentType("application/json");
        List<Transaction> transactions= null;
        try {
            transactions = service.getTransactionByUsername(sender);
        } catch (TransactionNotFoundException e) {
            e.printStackTrace();
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
        Gson gson=new Gson();
        String allTransaction=gson.toJson(transactions);
        resp.getWriter().println(allTransaction);
    }
}
