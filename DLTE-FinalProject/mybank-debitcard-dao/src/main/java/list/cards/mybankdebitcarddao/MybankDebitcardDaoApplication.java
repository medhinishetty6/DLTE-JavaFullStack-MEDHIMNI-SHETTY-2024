package list.cards.mybankdebitcarddao;


import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
import list.cards.mybankdebitcarddao.services.DebitCardService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class MybankDebitcardDaoApplication {
    public static void main(String[] args) throws SQLException {

        ConfigurableApplicationContext context=  SpringApplication.run(MybankDebitcardDaoApplication.class, args);
        DebitCardRepository debitCardService=context.getBean(DebitCardService.class);

    }
}
