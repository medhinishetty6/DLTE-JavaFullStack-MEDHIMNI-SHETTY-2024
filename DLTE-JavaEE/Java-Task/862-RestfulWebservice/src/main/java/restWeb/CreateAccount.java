package restWeb;

import org.example.entity.Account;
import org.example.middleware.DatabaseTarget;
import org.example.remote.StorageTarget;
import org.example.services.TransactionServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("CreateAccountServlet")
public class CreateAccount extends HttpServlet {
    public TransactionServices transactionService;
    public ResourceBundle resourceBundle;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        transactionService=new TransactionServices(storageTarget);
        resourceBundle=ResourceBundle.getBundle("application");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        long phoneNumber=Long.parseLong(req.getParameter("phoneNumber"));
        double balance=Double.parseDouble(req.getParameter("balance"));
        HttpSession session = req.getSession();
        Account account=new Account(username,password,email,phoneNumber,balance);
        RequestDispatcher dispatcher=req.getRequestDispatcher("create.jsp");
        transactionService.addAccount(account);
        req.setAttribute("info", resourceBundle.getString("account.create"));
        dispatcher.forward(req, resp);
    }

}
