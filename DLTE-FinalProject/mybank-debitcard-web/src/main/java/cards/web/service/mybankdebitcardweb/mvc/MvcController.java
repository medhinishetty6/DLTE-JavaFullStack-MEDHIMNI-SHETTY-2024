package cards.web.service.mybankdebitcardweb.mvc;

import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class MvcController {
    @Autowired
    DebitCardRepository debitCardRepository;

    @GetMapping("/")
    public String landing(){
        return "index";
    }
    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }
}
