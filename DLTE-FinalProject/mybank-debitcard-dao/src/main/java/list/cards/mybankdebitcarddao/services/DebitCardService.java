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

import java.rmi.ServerException;
import java.sql.*;
import java.util.*;

@Service
public class DebitCardService implements DebitCardRepository {

    Logger logger = LoggerFactory.getLogger(DebitCardService.class);

    // Autowired JdbcTemplate for database access
    @Autowired
    private JdbcTemplate jdbcTemplate;


    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    // Method to fetch debit card data from the database
    @Override
    public List<DebitCard> getDebitCard() throws SQLSyntaxErrorException, DebitCardException {
        List<DebitCard> debitCardList = null;
        try {
            // Query database to fetch debit card data
            debitCardList = jdbcTemplate.query("SELECT * FROM mybank_app_debitcard where not debitcard_status='Blocked'", new DebitCardMapper());
            // Log success message
            logger.info(resourceBundle.getString("card.fetch.success"));
        } catch (DataAccessException sqlException) {
            // Log error for SQL syntax exception
            logger.error(resourceBundle.getString("sql.syntax.invalid"));
            // Throw SQLSyntaxErrorException
            throw new SQLSyntaxErrorException(sqlException);
        }
        // Check if no data found
        if (debitCardList == null || debitCardList.isEmpty()) {
            // Log warning for empty result set
            logger.warn(resourceBundle.getString("card.list.null"));
            // Throw DebitCardException
            throw new DebitCardException(resourceBundle.getString("card.not.available"));
        }
        return debitCardList;
    }



    @Override
    public String activateStatus(DebitCard debitCard, Long debitCardNumber, Long customerId) throws SQLSyntaxErrorException, DebitCardException, DebitCardNullException {
        // Define a CallableStatementCreator to prepare the call to the stored procedure
        CallableStatementCreator creator = con -> {
            CallableStatement statement = con.prepareCall("{call activate_debitcard(?,?,?)}");
            // Set the parameters for the stored procedure call
            statement.setLong(1, debitCardNumber);
            statement.setLong(2, customerId);
            statement.registerOutParameter(3, Types.VARCHAR);
            return statement;
        };

        // Execute the stored procedure using JdbcTemplate
        Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
                new SqlParameter[]{
                        new SqlParameter(Types.NUMERIC),
                        new SqlParameter(Types.NUMERIC),
                        new SqlOutParameter("p_result", Types.VARCHAR)
                })
        );

        // Extract the result from the output parameter of the stored procedure
        String result = returnedExecution.get("p_result").toString();
        logger.info(result);
        // Handle different results from the stored procedure
        switch (result) {
            case "SQLSUCESS":
                // Log success and return appropriate message
                logger.info(resourceBundle.getString("card.active"));
                return resourceBundle.getString("card.active");
            case "SQLERR-004":
                // Log error and throw exception for card not found
                logger.error(resourceBundle.getString("activation.fail"));
                throw new DebitCardNullException("activation.fail");
            case "SQLERR-005":
                // Log error and throw exception for card already active
                logger.error(resourceBundle.getString("debitCard.already.active"));
                throw new DebitCardException("debitCard.already.active");
            case "SQLERR-003":
                logger.error(resourceBundle.getString("account.not.editable"));
                throw new CardNotEditableException("account.not.editable");
                // Handle any other unexpected result
            default:
                return null;
        }
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

























//    @Override
//    public String activateStatus(Long debitCardNumber) throws SQLSyntaxErrorException {
//        CallableStatementCreator creator = con -> {
//            CallableStatement statement = con.prepareCall("{call activate_debitcard(?,?)}");
//            statement.setLong(1, debitCardNumber);
//            statement.registerOutParameter(2, Types.VARCHAR);
//            return statement;
//        };
//
//        try {
//            Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Collections.emptyList());
//
//            // Check if the acknowledgment message is null
//            Object acknowledgmentObj = returnedExecution.get("p_acknowledgement");
//            String acknowledgement = acknowledgmentObj != null ? acknowledgmentObj.toString() : null;
//
//            if (acknowledgement != null) {
//                logger.info(acknowledgement);
//            } else {
//                logger.error("Acknowledgment message is null.");
//            }
//
//            return acknowledgement;
//        } catch (DataAccessException e) {
//            throw new SQLSyntaxErrorException("Database access error: " + e.getMessage());
//        }
//    }















//    @Override
//    public String activateStatus(Long debitCardNumber) throws SQLSyntaxErrorException {
//        CallableStatementCreator creator = con -> {
//            CallableStatement statement = con.prepareCall("{call activate_debitcard(?,?)}");
//            statement.setLong(1, debitCardNumber);
//            statement.registerOutParameter(2, Types.VARCHAR);
//            return statement;
//        };
//
//        try {
//            Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Collections.emptyList());
//
//            // Check if the acknowledgment message is null
//            Object acknowledgmentObj = returnedExecution.get("p_acknowledgement");
//            String acknowledgement = acknowledgmentObj != null ? acknowledgmentObj.toString() : null;
//
//            if (acknowledgement != null) {
//                logger.info(acknowledgement);
//            } else {
//                logger.error("Acknowledgment message is null.");
//            }
//
//            return acknowledgement;
//        } catch (DataAccessException e) {
//            throw new SQLSyntaxErrorException("Database access error: " + e.getMessage());
//        }
//    }



















































//    @Override
//    public DebitCard activateStatus(Long debitCardNumber) throws SQLSyntaxErrorException, DebitCardException{
//        DebitCard debitCard1 = null;
//        try {
//            // Query database to fetch debit card data
//            //  debitCardList = jdbcTemplate.query("UPDATE debitcard_status FROM mybank_app_debitcard where debitcard_status='Inactive'", new DebitCardMapper());
//            jdbcTemplate.update("UPDATE mybank_app_debitcard SET debitcard_status = ? where debitcard_number=?",new Object[]{"Active",debitCardNumber});
//            debitCard1 = jdbcTemplate.queryForObject("select * from mybank_app_debitcard where debitcard_number=?",new Object[]{debitCardNumber},new DebitCardMapper());
//
//            logger.info(resourceBundle.getString("card.fetch.success"));
//        } catch (DataAccessException sqlException) {
//            // Log error for SQL syntax exception
//            logger.error(resourceBundle.getString("sql.syntax.invalid"));
//            // Throw SQLSyntaxErrorException
//            throw new SQLSyntaxErrorException(sqlException);
//        }
//        // Check if no data found
//        if (debitCard1 == null) {
//            // Log warning for empty result set
//            logger.warn(resourceBundle.getString("card.list.null"));
//            // Throw DebitCardException
//            throw new DebitCardException(resourceBundle.getString("card.not.available"));
//        }
//        return debitCard1;
//        //  return null;
//    }