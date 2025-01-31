package restfulweb;

import com.google.gson.Gson;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.ReceiverNotFoundException;
import org.example.middleware.DatabaseTarget;
import org.example.remote.StorageTarget;
import org.example.services.TransactionServices;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/createTransaction/")
class CreateNewTransactionPost extends HttpServlet {
    TransactionServices transactionService;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        transactionService= new TransactionServices(storageTarget);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getReader().lines();
        Gson gson =new Gson();
        Transactions findByUsername=gson.fromJson(req.getReader(), Transactions.class);
        try {
            transactionService.transferFunds(findByUsername.getSender(),findByUsername.getReceiver(),findByUsername.getAmount());
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        } catch (ReceiverNotFoundException e) {
            e.printStackTrace();
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
        resp.getWriter().println("Transfer of amount "+findByUsername.getAmount()+" from "+findByUsername.getSender()+" to "+findByUsername.getReceiver());
        System.out.println("Transfer of amount "+findByUsername.getAmount()+" from "+findByUsername.getSender()+" to "+findByUsername.getReceiver());
    }

}

