package cards.web.service.mybankdebitcardweb.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import links.debitcard.ServiceStatus;
import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.exception.CardNotEditableException;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import list.cards.mybankdebitcarddao.exception.DebitCardNullException;
import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
import list.cards.mybankdebitcarddao.security.CardSecurity;
import list.cards.mybankdebitcarddao.security.CardSecurityServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    CardSecurityServices services;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("card");

    private Logger logger = LoggerFactory.getLogger(DebitCardController.class);

    // endpoint to activate debit card
    @PutMapping("/activate/{cardNumber}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Debit card does not exist or request body is empty"),
            @ApiResponse(responseCode = "400", description = "Debit card is already active or debit card number is wrong"),
    })
    public ResponseEntity<String> activateCard(@Valid @RequestBody DebitCard debitCard, @PathVariable("cardNumber") Long debitCardNumber) {
        try {
            // Getting the authentication details
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();
            CardSecurity card=services.findByUserName(username);   //searching card details for the particular username

            if (debitCard == null) {
                throw new IllegalArgumentException(resourceBundle.getString("empty.body"));
            }

            // Activating the debit card
            String response = debitCardRepository.activateStatus(debitCard,debitCardNumber,card.getCustomerId());
            logger.info(response);
            // Returning the response based on activation status
            if (response.equals(resourceBundle.getString("card.active"))) {
                logger.info(resourceBundle.getString("card.active"));
                return ResponseEntity.ok(response);
            } else {
                throw new DebitCardNullException(resourceBundle.getString("activation.fail"));
            }
        } catch (CardNotEditableException error) {
            logger.error(resourceBundle.getString("account.not.editable"));   //user does not have access to this account
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("account.not.editable"));
        } catch (SQLSyntaxErrorException syntaxError) {
            logger.error(resourceBundle.getString("internal.error"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("internal.error"));
        } catch (DebitCardException debitCardException) {
            logger.error(resourceBundle.getString("debitCard.already.active"));   //if the debit card is already active
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("debitCard.already.active"));
        } catch (DebitCardNullException debitCardNullException) {
            logger.error(resourceBundle.getString("activation.fail"));       //debit card does not exist
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("activation.fail"));
        } catch (IllegalArgumentException illegalArgumentException) {
            logger.error(resourceBundle.getString("empty.body"));       //if request body is empty
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("empty.body"));
        }
    }

    //  Handling validation exceptions
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // Extracting field errors and their error messages
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}



