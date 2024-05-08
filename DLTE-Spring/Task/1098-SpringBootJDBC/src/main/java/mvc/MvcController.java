package mvc;

import com.example.demo.Transaction;
import com.example.demo.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/web")
public class MvcController {
    @Autowired
    TransactionServices transactionServices;

    @GetMapping("/")
    public String securityLogin(){
        return "security";
    }
    @RequestMapping(value = "/newTransaction",method = RequestMethod.GET)
    public String requestToSubmission(Model model){
        Transaction transaction = new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "newTransaction.html";
    }
    @RequestMapping(value = "/index",method = RequestMethod.GET)
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


    @RequestMapping(value="/sender",method = RequestMethod.GET)
    public String findBySender(@RequestParam("sname") String sname, Model model){
        List<Transaction> transaction = transactionServices.apiFindBySender(sname);
        model.addAttribute("transactionsModels",transaction);
        return "sender";
    }


    @RequestMapping(value="/receiver",method = RequestMethod.GET)
    public String findByReceiver(@RequestParam("rname") String rname, Model model){
        List<Transaction> transaction = transactionServices.apiFindByReceiver(rname);
        model.addAttribute("transactionsModels",transaction);
        return "receiver";
    }

    @RequestMapping(value="/amount",method = RequestMethod.GET)
    public String findByAmount(@RequestParam("price") Long price, Model model){
        List<Transaction> transaction = transactionServices.apiFindByAmount(price);
        model.addAttribute("transactionsModels",transaction);
        return "amount";
    }


    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String blockCard(@RequestParam("start") String start, @RequestParam("end") String end , Model model){
        try{
            SimpleDateFormat dateFormatStart = new SimpleDateFormat(start);
            Date dateStart = dateFormatStart.parse(start);
            SimpleDateFormat dateFormatEnd = new SimpleDateFormat(end);
            Date dateEnd = dateFormatEnd.parse(end);
            String result = transactionServices.removeByDate(dateStart, dateEnd);
            if (result.contains("removed"))
                model.addAttribute("status",result + " transaction between " + start + " to " + end);
            else
                model.addAttribute("status", result + " not removed");
        }
        catch (TransactionException | ParseException cardException){
            model.addAttribute("error",cardException.toString());

        }
        return "removedate";

    }

}
