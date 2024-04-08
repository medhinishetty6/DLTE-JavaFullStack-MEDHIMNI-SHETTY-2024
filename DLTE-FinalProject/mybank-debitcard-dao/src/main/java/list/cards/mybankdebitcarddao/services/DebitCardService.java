package list.cards.mybankdebitcarddao.services;

import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class DebitCardService implements DebitCardRepository {

    // Logger for logging messages
    Logger LOGGER = LoggerFactory.getLogger(DebitCardService.class);

    // Autowired JdbcTemplate for database access
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ResourceBundle for accessing application properties
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    // Method to fetch debit card data from the database
    @Override
    public List<DebitCard> getDebitCard() throws SQLSyntaxErrorException, DebitCardException {
        List<DebitCard> debitCardList = null;
        try {
            // Query database to fetch debit card data
            debitCardList = jdbcTemplate.query("SELECT * FROM mybank_app_debitcard where not debitcard_status='Blocked'", new DebitCardMapper());
            // Log success message
            LOGGER.info(resourceBundle.getString("card.fetch.success"));
        } catch (DataAccessException sqlException) {
            // Log error for SQL syntax exception
            LOGGER.error(resourceBundle.getString("sql.syntax.invalid"));
            // Throw SQLSyntaxErrorException
            throw new SQLSyntaxErrorException(sqlException);
        }
        // Check if no data found
        if (debitCardList == null || debitCardList.isEmpty()) {
            // Log warning for empty result set
            LOGGER.warn(resourceBundle.getString("card.list.null"));
            // Throw DebitCardException
            throw new DebitCardException(resourceBundle.getString("card.not.available"));
        }
        return debitCardList;
    }

    // RowMapper class to map ResultSet to DebitCard object
    public class DebitCardMapper implements RowMapper<DebitCard> {
        @Override
        public DebitCard mapRow(ResultSet rs, int rowNum) throws SQLException {
            // Create DebitCard object and set its attributes
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
