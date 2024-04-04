package list.cards.mybankdebitcarddao;

import list.cards.mybankdebitcarddao.services.DebitCardService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MybankDebitcardDaoApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context=  SpringApplication.run(MybankDebitcardDaoApplication.class, args);
        DebitCardService debitCardService=context.getBean(DebitCardService.class);
        System.out.println(debitCardService.getDebitCard());
    }
}
