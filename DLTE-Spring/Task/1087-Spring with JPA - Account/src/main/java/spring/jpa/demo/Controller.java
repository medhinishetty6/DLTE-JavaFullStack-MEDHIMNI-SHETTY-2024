package spring.jpa.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountController")
public class Controller {
    @Autowired
    Services services;
    @PostMapping("/open")
    public AccountInformation save(@RequestBody AccountInformation accountInfo){
        return  services.saveAll(accountInfo);
    }
    @PutMapping(value = "/",consumes = "application/json")
    public AccountInformation update(AccountInformation accountInfo){
        return  services.saveAll(accountInfo);
    }

    @GetMapping("/findAll")
    public List<AccountInformation> findAll(){
        return services.find();
    }



}
