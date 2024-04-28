package mvc;

import com.example.demo.Transaction;
import com.example.demo.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MvcController {
    @Autowired
    TransactionServices transactionServices;

    @RequestMapping(value = "/newTransaction",method = RequestMethod.GET)
    public String requestToSubmission(Model model){
        Transaction transaction = new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "newTransaction.html";
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public String newTransaction(Transaction transaction, Model model, BindingResult bindingResult){
        try{
            if(!bindingResult.hasErrors()){
                transaction = transactionServices.apiSave(transaction);
                model.addAttribute("status",transaction.getTransactionId()+" has been inserted");
            }
        }catch(TransactionException transactionException){
            model.addAttribute("error",transactionException.getMessage());
        }
        return "newTransaction.html";
    }

}
