package cards.web.service.mybankdebitcardweb.soap;

import links.debitcard.DebitCard;
import links.debitcard.ServiceStatus;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


@ComponentScan("list.cards.mybankdebitcarddao")

@Endpoint
public class SoapPhase {
    // URL for SOAP request
    private final String url="http://debitcard.links";

    // Dependency injection of DebitCardService
    @Autowired
    private DebitCardRepository debitCardRepository;


    ResourceBundle resourceBundle = ResourceBundle.getBundle("card");

    private static final Logger logger = LoggerFactory.getLogger(SoapPhase.class);


    @PayloadRoot(namespace = url,localPart = "viewDebitCardRequest")
    @ResponsePayload
    public  ViewDebitCardResponse viewDebitCardResponse(@RequestPayload ViewDebitCardRequest viewDebitCardRequest) throws SQLException{

        ViewDebitCardResponse viewDebitCardResponse = new ViewDebitCardResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String username = authentication.getName();
            List<DebitCard> debitCardList = new ArrayList<>();             // Initialize a list to store DebitCard objects
            List<list.cards.mybankdebitcarddao.entities.DebitCard> debitCardsDao = debitCardRepository.getDebitCard(username);            // Retrieve DebitCard entities from the service

            // Convertion of DebitCard entities to DebitCard objects
            debitCardsDao.forEach(debitCard -> {
                links.debitcard.DebitCard currentDebitCard = new links.debitcard.DebitCard();
                Date date = debitCard.getDebitCardExpiry();
                XMLGregorianCalendar xmlCalendar = null;
                try {
                    xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(date.toString());
                } catch (DatatypeConfigurationException e) {
                    e.printStackTrace();
                }
                currentDebitCard.setDebitCardExpiry(xmlCalendar);
                BeanUtils.copyProperties(debitCard, currentDebitCard);
                debitCardList.add(currentDebitCard);
            });
            debitCardList.forEach(System.out::println);

            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            viewDebitCardResponse.getDebitCard().addAll(debitCardList);          // here you will be adding the DebitCard objects
        } catch (DebitCardException e) {

            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage(e.toString());
            logger.error(resourceBundle.getString("Debitcard.error"));
        }

        // here set the service status in the response and return it
        viewDebitCardResponse.setServiceStatus(serviceStatus);
        return viewDebitCardResponse;
    }


    @ExceptionHandler(value = {SQLException.class, DebitCardException.class})
    public ResponseEntity<String> handleExceptions(Exception ex) {

        logger.info(resourceBundle.getString("Debitcard.error"));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}



