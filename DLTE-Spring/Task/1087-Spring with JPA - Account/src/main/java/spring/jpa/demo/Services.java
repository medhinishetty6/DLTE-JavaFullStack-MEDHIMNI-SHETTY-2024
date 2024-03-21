package spring.jpa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services {
    @Autowired
    AccountRepo accountRepo;

    public AccountInformation saveAll(AccountInformation accountInformation){
        return accountRepo.save(accountInformation);
    }
    public List<AccountInformation> find(){
        return (List<AccountInformation>) accountRepo.findAll();
    }
}
