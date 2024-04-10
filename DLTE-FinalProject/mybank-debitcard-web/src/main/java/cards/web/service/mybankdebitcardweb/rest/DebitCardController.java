package cards.web.service.mybankdebitcardweb.rest;

import cards.web.service.mybankdebitcardweb.soap.SoapPhase;
import links.debitcard.ServiceStatus;
import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLSyntaxErrorException;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/debitcard")
public class DebitCardController {
    @Autowired
    private DebitCardRepository debitCardRepository;

    // ResourceBundle for accessing application properties/messages
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    // Logger for logging messages
    private static final Logger logger = LoggerFactory.getLogger(DebitCardRepository.class);

    @PutMapping("/activate/{cardNumber}")
    public ResponseEntity<ServiceStatus> activateCard(@RequestBody DebitCard debitCard1,@PathVariable("cardNumber") String debitCardNumber) {
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            debitCardRepository.activateStatus(Long.valueOf(debitCardNumber));
            logger.info(resourceBundle.getString("card.active"));
            serviceStatus.setStatus( HttpStatus.OK.value());
            serviceStatus.setMessage("Card updated successfully");
            return new ResponseEntity<>(serviceStatus, HttpStatus.OK);

        } catch (SQLSyntaxErrorException syntaxError) {
            logger.error(resourceBundle.getString("internal.error"));
            return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DebitCardException debitCardException) {
            logger.warn(resourceBundle.getString("activation.fail"));
            return new ResponseEntity<>(serviceStatus, HttpStatus.BAD_REQUEST);
        }
    }


}














//    @PutMapping("/activate/{cardNumber}")
//    public ResponseEntity<ServiceStatus> activateCard(@RequestBody DebitCard debitCard1,@PathVariable("cardNumber") String debitCardNumber) {
//        ServiceStatus serviceStatus = new ServiceStatus();
//        try {
//            debitCardRepository.activateStatus(Long.valueOf(debitCardNumber));
//            serviceStatus.setStatus(HttpStatus.OK.value());
//            serviceStatus.setMessage(resourceBundle.getString("Card.active"));
//            return new ResponseEntity<>(serviceStatus, HttpStatus.OK);
//        } catch (SQLSyntaxErrorException syntaxError) {
//            logger.error(resourceBundle.getString("internal.error"));
//            serviceStatus.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            serviceStatus.setMessage("internal.error");
//            return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
//        } catch (DebitCardException debitCardException) {
//            // logger.warn(resourceBundle.getString("activation.fail"));
//            logger.warn("activation.fail" + debitCardNumber);
//            // Set error status
//            serviceStatus.setStatus(HttpStatus.BAD_REQUEST.value());
//            serviceStatus.setMessage("activation.fail");
//            return new ResponseEntity<>(serviceStatus, HttpStatus.BAD_REQUEST);
//        }
//    }







































//        // Helper method to find debit card by number
//    private DebitCard findDebitCardByNumber(String cardNumber) {
//        // Implement this method to fetch debit card by number from your database
//   return null;
   // }



//  @PutMapping("/activate/{cardNumber}")
//    public ResponseEntity<ServiceStatus> activateCard(@PathVariable String debitCardNumber, @RequestBody DebitCard debitCard1) {
//
//        ServiceStatus serviceStatus = new ServiceStatus();
//        try {
//List<DebitCard> debitCard=debitCardService.activateStatus();
//            // Check if the card exists and is inactive
//           // DebitCard debitCard =activateStatus(debitCardStatus);
//            if (debitCard != null && debitCard.get(0).getDebitCardStatus().equalsIgnoreCase("Inactive")) {
//                // Update card status to active
//                updateCardStatus(debitCard, "Active");
//                // Log success message
//                logger.info("Card " + debitCardNumber + " activated successfully.");
//                // Set success status
//                serviceStatus.setStatus(HttpStatus.OK.value());
//                serviceStatus.setMessage("Card activated successfully.");
//                return new ResponseEntity<>(serviceStatus, HttpStatus.OK);
//            } else {
//                // Log warning for invalid card number or already active card
//                logger.warn("Invalid card number or already active card: " + debitCardNumber);
//                // Set error status
//                serviceStatus.setStatus(HttpStatus.BAD_REQUEST.value());
//                serviceStatus.setMessage("Invalid card number or already active card.");
//                return new ResponseEntity<>(serviceStatus, HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            // Log error
//            logger.error("Error activating card " + debitCardNumber + ": " + e.getMessage());
//            // Set error status
//            serviceStatus.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            serviceStatus.setMessage("Error activating card: " + e.getMessage());
//            return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

