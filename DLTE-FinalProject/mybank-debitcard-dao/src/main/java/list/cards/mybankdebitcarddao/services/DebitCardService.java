package list.cards.mybankdebitcarddao.services;

import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.exception.CardNotEditableException;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import list.cards.mybankdebitcarddao.exception.DebitCardNullException;
import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class DebitCardService implements DebitCardRepository {

    Logger logger = LoggerFactory.getLogger(DebitCardService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    // debit card data is fetched from the database
    @Override

    public List<DebitCard> getDebitCard(String username) {
        List<DebitCard> debitCardList = null;
        try {
            debitCardList = jdbcTemplate.query("SELECT d.debitcard_number,d.account_number,d.customer_id,d.debitcard_cvv,d.debitcard_pin,d.debitcard_expiry,d.debitcard_status,d.debitcard_domestic_limit,d.debitcard_international_limit FROM mybank_app_debitcard d JOIN mybank_app_customer c ON d.customer_id = c.customer_id JOIN mybank_app_account a on a.account_number=d.account_number WHERE  c.username = ?", new Object[]{username}, new DebitCardMapper());
            logger.info(resourceBundle.getString("card.fetch.success"));
        } catch (DataAccessException sqlException) {
            logger.error(resourceBundle.getString("sql.syntax.invalid"));
            throw new DebitCardException(resourceBundle.getString("sql.syntax.invalid"));
        }
        if (debitCardList.size() == 0) {
            logger.warn(resourceBundle.getString("card.list.null"));
            throw new DebitCardNullException();
        }
        return debitCardList;
    }




    @Override

    public String activateStatus(DebitCard debitCard, Long debitCardNumber, Long customerId) throws SQLSyntaxErrorException, DebitCardException, DebitCardNullException {
        // Preparing the call to the stored procedure
        CallableStatementCreator creator = con -> {
            CallableStatement statement = con.prepareCall("{call activate_debitcard(?,?,?)}");
            // Setting parameters for the stored procedure call
            statement.setLong(1, debitCardNumber);
            statement.setLong(2, customerId);
            statement.registerOutParameter(3, Types.VARCHAR);
            return statement;
        };


        Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
                new SqlParameter[]{
                        new SqlParameter(Types.NUMERIC),
                        new SqlParameter(Types.NUMERIC),
                        new SqlOutParameter("p_result", Types.VARCHAR)
                })
        );         // Execution of stored procedure

        String result = returnedExecution.get("p_result").toString();
        logger.info(result);

        // here different results are handled from the stored procedure
        switch (result) {
            case "SQLSUCESS"://activation successful
                logger.info(resourceBundle.getString("card.active"));
                return resourceBundle.getString("card.active");
            case "SQLERR-004"://card not found
                logger.error(resourceBundle.getString("activation.fail"));
                throw new DebitCardNullException("activation.fail");
            case "SQLERR-005"://card already active
                logger.error(resourceBundle.getString("debitCard.already.active"));
                throw new DebitCardException("debitCard.already.active");
            case "SQLERR-003"://user does not have excess for the particular account
                logger.error(resourceBundle.getString("account.not.editable"));
                throw new CardNotEditableException("account.not.editable");
            default:
                return null;
        }
    }


    //  Maping ResultSet to DebitCard object
    public class DebitCardMapper implements RowMapper<DebitCard> {
        @Override
        public DebitCard mapRow(ResultSet rs, int rowNum) throws SQLException {
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


