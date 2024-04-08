package cards.web.service.mybankdebitcardweb.soap;

import links.debitcard.DebitCard;
import links.debitcard.ServiceStatus;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
import list.cards.mybankdebitcarddao.services.DebitCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

// Component scan to find beans for dependency injection
@ComponentScan("list.cards.mybankdebitcarddao")
// Annotation to declare this class as an endpoint for SOAP requests
@Endpoint
public class SoapPhase {
    // URL namespace for SOAP requests
    private final String url="http://debitcard.links";

    // Dependency injection of DebitCardService
    @Autowired
    private DebitCardRepository debitCardService;

    // ResourceBundle for accessing application properties/messages
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    // Logger for logging messages
    private static final Logger logger = LoggerFactory.getLogger(SoapPhase.class);

    // Method to handle incoming SOAP requests
    @PayloadRoot(namespace = url,localPart = "viewDebitCardRequest")
    @ResponsePayload
    public  ViewDebitCardResponse viewDebitCardResponse(@RequestPayload ViewDebitCardRequest viewDebitCardRequest) throws SQLException{
        // Create a response object
        ViewDebitCardResponse viewDebitCardResponse = new ViewDebitCardResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            // Initialize a list to store DebitCard objects
            List<DebitCard> debitCardList = new ArrayList<>();
            // Retrieve DebitCard entities from the service
            List<list.cards.mybankdebitcarddao.entities.DebitCard> debitCardsDao = debitCardService.getDebitCard();

            // Convert DebitCard entities to DebitCard objects
            debitCardsDao.forEach(debitCard -> {
                links.debitcard.DebitCard currentDebitCard = new links.debitcard.DebitCard();
                BeanUtils.copyProperties(debitCard, currentDebitCard);
                debitCardList.add(currentDebitCard);
            });

            // Set the response status to OK
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            // Add the DebitCard objects to the response
            viewDebitCardResponse.getDebitCard().addAll(debitCardList);
        } catch (DebitCardException e) {
            // Handle exceptions by setting an internal server error status and logging the error
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage(e.toString());
            logger.error(resourceBundle.getString("Debitcard.error"));
        }

        // Set the service status in the response and return it
        viewDebitCardResponse.setServiceStatus(serviceStatus);
        return viewDebitCardResponse;
    }

    // Exception handler to handle SQLException and DebitCardException
    @ExceptionHandler(value = {SQLException.class, DebitCardException.class})
    public ResponseEntity<String> handleExceptions(Exception ex) {
        // Log the error
        logger.info(resourceBundle.getString("Debitcard.error"));
        // Return an internal server error response with the exception message
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
































//package cards.web.service.mybankdebitcardweb.soap;
//
//
//import links.debitcard.DebitCard;
//import links.debitcard.ServiceStatus;
//import links.debitcard.ViewDebitCardRequest;
//import links.debitcard.ViewDebitCardResponse;
//import list.cards.mybankdebitcarddao.exception.DebitCardException;
//import list.cards.mybankdebitcarddao.services.DebitCardService;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//
//import javax.servlet.http.HttpServletResponse;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@ComponentScan("list.cards.mybankdebitcarddao")
//@Endpoint
//public class SoapPhase {
//    private final String url="http://debitcard.links";
//    @Autowired
//    private DebitCardService debitCardService;
//
//    @PayloadRoot(namespace = url,localPart = "viewDebitCardRequest")
//    @ResponsePayload
//    public ViewDebitCardResponse viewDebitCardResponse(@RequestPayload ViewDebitCardRequest viewDebitCardRequest) {
//        ViewDebitCardResponse viewDebitCardResponse = new ViewDebitCardResponse();
//        ServiceStatus serviceStatus = new ServiceStatus();
//      try {
//          List<DebitCard> debitCardList = new ArrayList<>();
//          List<list.cards.mybankdebitcarddao.entities.DebitCard> debitCardsDao = debitCardService.getDebitCard();
//
//          debitCardsDao.forEach(debitCard -> {
//              links.debitcard.DebitCard currentDebitCard = new links.debitcard.DebitCard();
//              BeanUtils.copyProperties(debitCard, currentDebitCard);
//              debitCardList.add(currentDebitCard);
//          });
//          serviceStatus.setStatus(HttpServletResponse.SC_OK);
//          viewDebitCardResponse.getDebitCard().addAll(debitCardList);
//      }catch (DebitCardException e){
//          serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//          serviceStatus.setMessage(e.toString());
//      }
//        viewDebitCardResponse.setServiceStatus(serviceStatus);
//        return  viewDebitCardResponse;
//    }
//
//}































//package cards.web.service.mybankdebitcardweb.soap;
//
//import links.debitcard.ServiceStatus;
//import links.debitcard.ViewDebitCardRequest;
//import links.debitcard.ViewDebitCardResponse;
//import list.cards.mybankdebitcarddao.entities.DebitCard;
//import list.cards.mybankdebitcarddao.exception.DebitCardException;
//import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.xml.bind.JAXBException;
//import javax.xml.datatype.DatatypeConfigurationException;
//import javax.xml.ws.Response;
//import java.sql.SQLException;
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//
//@ComponentScan("dlist.cards.mybankdebitcarddao")
//@Endpoint
//public class SoapPhase {
//
//    private final String url = "http://debitcard.links";
//
//    @Autowired
//    private DebitCardRepository debitCardRepository;
//
//    private Logger LOGGER = LoggerFactory.getLogger(SoapPhase.class);
//    private ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
//
//    @PayloadRoot(namespace = url, localPart = "viewDebitCardRequest")
//    @ResponsePayload
//    public ViewDebitCardResponse viewDebitCardResponse(@RequestPayload ViewDebitCardRequest viewDebitCardRequest)
//            throws SQLException, JAXBException, DatatypeConfigurationException {
//        ViewDebitCardResponse viewDebitCardResponse = new ViewDebitCardResponse();
//        ServiceStatus serviceStatus = new ServiceStatus();
//        try {
//            List<DebitCard> debitCardList = new ArrayList<>();
//            List<list.cards.mybankdebitcarddao.entities.DebitCard> debitCardsDao = debitCardRepository.getDebitCard();
//            // Using lambda function for performing BeanUtils.copyProperties
//            debitCardsDao.forEach(debitCardDao -> {
//                list.cards.mybankdebitcarddao.entities.DebitCard currentDebitCard = new list.cards.mybankdebitcarddao.entities.DebitCard();
//                BeanUtils.copyProperties(debitCardsDao, currentDebitCard);
//                debitCardList.add(currentDebitCard);
//            });
//            serviceStatus.setStatus(HttpServletResponse.SC_OK);
//            serviceStatus.setMessage(resourceBundle.getString("card.fetch.success"));
//            viewDebitCardResponse.getDebitCard().addAll(debitCardList);
//        } catch (SQLSyntaxErrorException e) {
//            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            LOGGER.error(resourceBundle.getString("soap.sql.error") + e + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            serviceStatus.setMessage(resourceBundle.getString("soap.db.error"));
//        } catch (DebitCardException e) {
//            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
//            LOGGER.error(resourceBundle.getString("cards.data.null") + e + HttpServletResponse.SC_NO_CONTENT);
//            serviceStatus.setMessage(resourceBundle.getString("cards.data.null"));
//        }
//        viewDebitCardResponse.setServiceStatus(serviceStatus);
//        return viewDebitCardResponse;
//    }
//
//    @ExceptionHandler(value = {SQLException.class, SQLSyntaxErrorException.class,
//            DebitCardException.class, JAXBException.class, DatatypeConfigurationException.class})
//    @ResponseStatus(value = HttpServletRespon.SC_INTERNAL_SERVER_ERROR)
//    public Response handleExceptions(Exception ex) {
//        LOGGER.error("Exception occurred: " + ex);
//        return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
//    }
//}