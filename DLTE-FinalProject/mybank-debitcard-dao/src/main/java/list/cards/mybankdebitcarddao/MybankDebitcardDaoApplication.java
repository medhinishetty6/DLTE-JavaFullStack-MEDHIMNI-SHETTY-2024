package list.cards.mybankdebitcarddao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class MybankDebitcardDaoApplication {
    public static void main(String[] args) throws SQLException {
         SpringApplication.run(MybankDebitcardDaoApplication.class, args);

    }
}
