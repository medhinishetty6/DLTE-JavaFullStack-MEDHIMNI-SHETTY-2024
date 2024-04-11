package list.cards.mybankdebitcarddao.remotes;

import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

//Interface of Debit Card Remotes
@Repository
public interface DebitCardRepository {
    //List of Debit cards will be Fetched
    public List<DebitCard> getDebitCard() throws  SQLException;
  public   String activateStatus(Long debitCardNumber) throws SQLSyntaxErrorException;
}














//// Repository interface for accessing debit card data
//@Repository
//public interface DebitCardRepository {
//
//    // Method to retrieve debit card data
//    List<DebitCard> getDebitCard() throws SQLException, DebitCardException;
//}
