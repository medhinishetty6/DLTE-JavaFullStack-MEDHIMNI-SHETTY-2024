package list.cards.mybankdebitcarddao.services;

import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class DebitCardService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public List<DebitCard> getDebitCard() {
        List<DebitCard> debitCardList = jdbcTemplate.query("select * from mybank_app_debitcard",new DebitCardMapper());
        return debitCardList;
   }
   protected class DebitCardMapper implements RowMapper<DebitCard>{
        @Override
        public DebitCard mapRow(ResultSet rs, int rowNum) throws SQLException{
            DebitCard debitCard = new DebitCard();
            debitCard.setDebitCardNumber(rs.getLong(1));
            debitCard.setAccountNumber(rs.getLong(2));
            debitCard.setCustomerId(rs.getInt(3));
            debitCard.setDebitCardCvv(rs.getInt(4));
            debitCard.setDebitCardPin(rs.getInt(5));
            debitCard.setDebitCardExpiry(rs.getDate(6));
            debitCard.setDebitCardStatus(rs.getString(7));
            debitCard.setDomesticLimit(rs.getDouble(8));
            debitCard.setInternationalLimit(rs.getDouble(9));
            return debitCard;
        }
   }
}
