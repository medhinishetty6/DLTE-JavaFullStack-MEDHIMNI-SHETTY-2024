package cards.web.service.mybankdebitcardweb.rest;

import cards.web.service.mybankdebitcardweb.soap.SoapPhase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import links.debitcard.ServiceStatus;
import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import list.cards.mybankdebitcarddao.exception.DebitCardNullException;
import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Debit card does not exist or request body is empty"),
            @ApiResponse(responseCode = "400", description = "Debit card is already active or debit card number is wrong"),

    })
    public ResponseEntity<String> activateCard(@Valid @RequestBody DebitCard debitCard, @PathVariable("cardNumber") Long debitCardNumber) {
        try {
            if (debitCard == null) {
                throw new IllegalArgumentException(resourceBundle.getString("empty.body"));
            }

            String response = debitCardRepository.activateStatus(debitCard,debitCardNumber);
            if (response.equals("Debit card activation successful.")) {
                logger.info(resourceBundle.getString("card.active"));
                return ResponseEntity.ok(response);
            } else {
                throw new DebitCardNullException(resourceBundle.getString("activation.fail"));
            }
        } catch (SQLSyntaxErrorException syntaxError) {
            logger.error(resourceBundle.getString("internal.error"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("internal.error"));
        } catch (DebitCardException debitCardException) {
            logger.error(resourceBundle.getString("debitCard.already.active"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("debitCard.already.active"));
        } catch (DebitCardNullException debitCardNullException) {
            logger.error(resourceBundle.getString("activation.fail"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("activation.fail"));
        } catch (IllegalArgumentException illegalArgumentException) {
            logger.error(resourceBundle.getString("empty.body"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("empty.body"));
        }
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}





































//    @PutMapping("/activate/{cardNumber}")
//    public ResponseEntity<String> activateCard( @Valid @RequestBody DebitCard debitCard,
//            @Valid @PathVariable("cardNumber") Long debitCardNumber,
//            BindingResult bindingResult) {
//
//        // Check for validation errors in the request body
//        if (bindingResult.hasErrors()) {
//            // Prepare error messages map
//            Map<String, String> errors = new HashMap<>();
//            for (FieldError error : bindingResult.getFieldErrors()) {
//                errors.put(error.getField(), error.getDefaultMessage());
//            }
//            // Return bad request response with error messages
//            return ResponseEntity.badRequest().body(errors.toString());
//        }
//
//        try {
//            String response = debitCardRepository.activateStatus(debitCardNumber);
//            if (response.equals("Debit card activation successful.")) {
//                logger.info(resourceBundle.getString("card.active"));
//                return ResponseEntity.ok(response);
//            } else {
//                throw new DebitCardNullException(resourceBundle.getString("activation.fail"));
//            }
//        } catch (SQLSyntaxErrorException syntaxError) {
//            logger.error(resourceBundle.getString("internal.error"));
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("internal.error"));
//        } catch (DebitCardException debitCardException) {
//            logger.error(resourceBundle.getString("debitCard.already.active"));
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("debitCard.already.active"));
//        } catch(DebitCardNullException debitCardNullException){
//            logger.error(resourceBundle.getString("activation.fail"));
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("activation.fail"));
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

