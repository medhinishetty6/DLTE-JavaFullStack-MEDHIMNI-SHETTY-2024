package cards.web.service.mybankdebitcardweb.mvc;

import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
import list.cards.mybankdebitcarddao.security.CardSecurity;
import list.cards.mybankdebitcarddao.security.CardSecurityServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ResourceBundle;

@Controller
@RequestMapping("/card")
public class CardController {
    @Autowired
    DebitCardRepository debitCardRepository;

    @Autowired
    CardSecurityServices cardSecurityServices;

    Logger logger= LoggerFactory.getLogger(CardController.class);

    ResourceBundle bundle=ResourceBundle.getBundle("card");

    @GetMapping("/login")
    public String index(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }


    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public String debitCardDetails(){
        return "view";
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(){
        return "error";
    }

    @GetMapping("/name")
    @ResponseBody
    public String CustomerName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        CardSecurity cardSecurity = cardSecurityServices.findByUserName(name);
        return cardSecurity.getCustomerName();
    }



}
