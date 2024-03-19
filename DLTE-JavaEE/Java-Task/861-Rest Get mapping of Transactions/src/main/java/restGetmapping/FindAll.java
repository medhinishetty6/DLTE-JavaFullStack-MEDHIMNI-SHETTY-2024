package restGetmapping;


import com.google.gson.Gson;
import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.remote.StorageTarget;
import org.example.services.TransactionServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findAll")
public class FindAll extends HttpServlet {
    TransactionServices services;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //want result in json format
        resp.setContentType("application/json");
        //find all users
        List<Transaction> transactionList=services.getAllTransactions();
        Gson gson=new Gson();
        //convert list to json
        String transaction=gson.toJson(transactionList);
        resp.getWriter().println(transaction);
    }

    @Override
    public void init() throws ServletException{
        StorageTarget storageTarget=new DatabaseTarget();
        services=new TransactionServices(storageTarget);
    }





}
