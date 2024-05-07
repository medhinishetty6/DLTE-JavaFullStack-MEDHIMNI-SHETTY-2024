package list.cards.mybankdebitcarddao.remotes;

import list.cards.mybankdebitcarddao.entities.DebitCard;
import org.springframework.stereotype.Repository;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

//Interface of Debit Card Remotes
@Repository
public interface DebitCardRepository {
    //List of Debit cards will be Fetched
    List<DebitCard> getDebitCard(String username);
    public String activateStatus(DebitCard debitCard, Long debitCardNumber, Long customerId) throws SQLSyntaxErrorException;
}







