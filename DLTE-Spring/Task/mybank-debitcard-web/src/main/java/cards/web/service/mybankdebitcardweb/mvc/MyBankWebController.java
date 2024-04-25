package cards.web.service.mybankdebitcardweb.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/web")
public class MyBankWebController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String myDashboard(){
        return "index";
    }

    @RequestMapping(value = "/viewDebitcard",method = RequestMethod.GET)
    public String viewDebitCard(){
        return "viewDebitcard";
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String update(){
        return "update";
    }
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        return "index";
    }

}
