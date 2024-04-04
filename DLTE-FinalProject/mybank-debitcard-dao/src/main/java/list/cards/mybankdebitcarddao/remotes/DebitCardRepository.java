package list.cards.mybankdebitcarddao.remotes;

import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface DebitCardRepository {

    List<DebitCard> getDebitCard() throws SQLException, DebitCardException;
}
