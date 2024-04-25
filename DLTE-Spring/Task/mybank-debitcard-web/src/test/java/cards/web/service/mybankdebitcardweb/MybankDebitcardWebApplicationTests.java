//package cards.web.service.mybankdebitcardweb;
//
//import cards.web.service.mybankdebitcardweb.soap.SoapPhase;
//import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
//import links.debitcard.DebitCard;
//import links.debitcard.ServiceStatus;
//import links.debitcard.ViewDebitCardRequest;
//import links.debitcard.ViewDebitCardResponse;
//import list.cards.mybankdebitcarddao.exception.DebitCardException;
//import list.cards.mybankdebitcarddao.services.DebitCardService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.xml.datatype.DatatypeConfigurationException;
//import java.math.BigInteger;
//import java.sql.SQLException;
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class MybankDebitcardWebApplicationTests {
//
//    @MockBean
//    private DebitCardService debitCardService;
//
//    @InjectMocks
//    private SoapPhase soapPhase;
//
//
//@Test
//public void testViewDebitCardResponse() throws DatatypeConfigurationException, DebitCardException, SQLException {
//    // Mocking the DebitCardService response
//    List<list.cards.mybankdebitcarddao.entities.DebitCard> debitCardList = new ArrayList<>();
//    list.cards.mybankdebitcarddao.entities.DebitCard entity = new list.cards.mybankdebitcarddao.entities.DebitCard();
//    list.cards.mybankdebitcarddao.entities.DebitCard debitCard= new list.cards.mybankdebitcarddao.entities.DebitCard(8965767890967542L,99543456789125L,12345,234,1674,new java.util.Date(2024,04,9), "active", 2000.0,50000.0);
//    //list.cards.mybankdebitcarddao.entities.DebitCard debitCard1 = new list.cards.mybankdebitcarddao.entities.DebitCard(8876945907634225L,89077956789126L,12346,564,2223,new java.util.Date(2024,04,11), "inactive", 4000.0,70000.0);
//    //list.cards.mybankdebitcarddao.entities.DebitCard debitCard2 = new list.cards.mybankdebitcarddao.entities.DebitCard(8923167890123943L, 45601234567827L, 12347, 555, 6543, new java.util.Date(2024, 04, 04), "active", 3000.0, 60000.0);
//    //debitCardList.add(debitCard);
//
//    // Set properties for entity
//    debitCardList.add(debitCard);
//    when(debitCardService.getDebitCard()).thenReturn(debitCardList);
//
//    // Creating request object
//    ViewDebitCardRequest request = new ViewDebitCardRequest();
//
//    // Calling the method
//    ViewDebitCardResponse response = soapPhase.viewDebitCardResponse(request);
//
//    // Verifying the response
//    assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
//    assertEquals(1, response.getDebitCard().size()); // Assuming one debit card is returned
//}
//
//
//
//    @Test
//    public void testFailDebitCardResponse() throws DatatypeConfigurationException, DebitCardException, SQLException {
//        // Mocking the DebitCardService response
//        List<list.cards.mybankdebitcarddao.entities.DebitCard> debitCardList = new ArrayList<>();
//      //  list.cards.mybankdebitcarddao.entities.DebitCard entity = new list.cards.mybankdebitcarddao.entities.DebitCard();
//        list.cards.mybankdebitcarddao.entities.DebitCard debitCard= new list.cards.mybankdebitcarddao.entities.DebitCard(8965767890967542L,99543456789125L,12345,234,1674,new java.util.Date(2024,04,9), "active", 2000.0,50000.0);
//        list.cards.mybankdebitcarddao.entities.DebitCard debitCard1 = new list.cards.mybankdebitcarddao.entities.DebitCard(8876945907634225L,89077956789126L,12346,564,2223,new java.util.Date(2024,04,11), "inactive", 4000.0,70000.0);list.cards.mybankdebitcarddao.entities.DebitCard debitCard2 = new list.cards.mybankdebitcarddao.entities.DebitCard(8923167890123943L, 45601234567827L, 12347, 555, 6543, new java.util.Date(2024, 04, 04), "active", 3000.0, 60000.0);
//        debitCardList.add(debitCard);
//        debitCardList.add(debitCard1);
//        when(debitCardService.getDebitCard()).thenReturn(debitCardList);
//
//        ViewDebitCardRequest request = new ViewDebitCardRequest();
//
//        // Calling the method
//        ViewDebitCardResponse response = soapPhase.viewDebitCardResponse(request);
//        assertNotEquals(1, response.getDebitCard().size()); // Assuming one debit card is returned
//        assertEquals(1234,debitCardList.get(1).getCustomerId());
//    }
//
//}

















































































//
//
//
////    @Test
////    void testAllDebitCards_failure() throws SQLException, DebitCardException {
////        ViewDebitCardRequest viewDebitCardRequest = new ViewDebitCardRequest();
////
////        List<DebitCard> debitCardList = new ArrayList<>();
////
////
////
////        list.cards.mybankdebitcarddao.entities.DebitCard debitCard= new list.cards.mybankdebitcarddao.entities.DebitCard(8965767890967542L,99543456789125L,12345,234,1674,new java.util.Date(2024,04,9), "active", 2000.0,50000.0);
////        list.cards.mybankdebitcarddao.entities.DebitCard debitCard1 = new list.cards.mybankdebitcarddao.entities.DebitCard(8876945907634225L,89077956789126L,12346,564,2223,new java.util.Date(2024,04,11), "inactive", 4000.0,70000.0);
////        list.cards.mybankdebitcarddao.entities.DebitCard debitCard2 = new list.cards.mybankdebitcarddao.entities.DebitCard(8923167890123943L, 45601234567827L, 12347, 555, 6543, new java.util.Date(2024, 04, 04), "active", 3000.0, 60000.0);
////        list.cards.mybankdebitcarddao.entities.DebitCard debitCard3 = new list.cards.mybankdebitcarddao.entities.DebitCard(8796543210988877L, 23432109876528L, 12348, 877, 6782, new Date(2024, 04, 29), "blocked", 5000.0, 80000.0);
////        //Add some dummy data into the arrayList for testing
////        debitCardList = Stream.of(debitCard,debitCard1,debitCard2,debitCard3).collect(Collectors.toList());
////
////        when(debitCardService.getDebitCard()).thenReturn(debitCardList);
////        // Execute the method under test
////        ViewDebitCardResponse response = soapPhase.viewDebitCardResponse(viewDebitCardRequest);
////        assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getServiceStatus().getStatus()); //FAIL
////
////        assertSame(3,response.getDebitCard().size());//fail
////
////    }
////    @Test
////    public void FetchAllDebitCard_DebitCardException() throws SQLException {
////        // Create a new ViewDebitCardRequest object
////        ViewDebitCardRequest viewDebitCardRequest = new ViewDebitCardRequest();
////
////        // Mock the behavior of the debitCardServices.getDebitCard() method to throw DebitCardException
////        when(debitCardService.getDebitCard()).thenThrow(DebitCardException.class);
////
////        // Execute the method under test
////        ViewDebitCardResponse response = SoapPhase.viewDebitCardResponce(viewDebitCardRequest);
////
////        // Assert the response
////        assertEquals(HttpServletResponse.SC_NO_CONTENT, response.getServiceStatus().getStatus());
////        assertEquals("No debit cards available", response.getServiceStatus().getMessage());
////    }
//
////
//
////   @Test
////   public void filterbyStatus() throws SQLSyntaxErrorException, DebitCardException{
////       List<DebitCard> debitCardList=new ArrayList<>();
////       DebitCard debitCard=new DebitCard();
////       debitCard.setDebitCardNumber(8734562534786345L);
////       debitCard.setAccountNumber(98234456372333L);
////       debitCard.setCustomerId(BigInteger.valueOf(1234));
////       debitCard.setDebitCardCvv(BigInteger.valueOf(222));
////       debitCard.setDebitCardPin(BigInteger.valueOf(1232));
////       debitCard.setDebitCardExpiry(XMLGregorianCalendarImpl.createDate(2024, 1, 10, 0));
////       debitCard.setDebitCardStatus("Active");
////       debitCard.setDomesticLimit(20000.00);
////       debitCard.setInternationalLimit(500000.00);
////
////       when(debitCardService.getDebitCard()).thenReturn(debitCardList);
////       ViewDebitCardRequest request=new ViewDebitCardRequest();
////       request.
////       when(accService.filterByStatus(1L)).thenReturn(accountList);
////
////       FilterByStatusRequest request = new FilterByStatusRequest();
////       request.setCustomerId(1L);
////       FilterByStatusResponse response = soapPhase.filterStatus(request);
////
////       // checking the response is success or not
////       assertEquals("Account fetched successfully",response.getServiceStatus().getMessage());//success
////       //assertEquals("Account not fetched",response.getServiceStatus().getMessage());
//   //}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    @Test//fail
////    void viewDebitCardResponse_Exception() throws DebitCardException {
////        // Mocking DebitCardService to throw exception
////        when(debitCardService.getDebitCard()).thenThrow(DebitCardException.class);
////
////        // Creating request object
////        ViewDebitCardRequest request = new ViewDebitCardRequest();
////
////        // Invoking method under test
////        ViewDebitCardResponse response = soapPhase.viewDebitCardResponse(request);
////
////        // Asserting response status and message
////        assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getServiceStatus().getStatus());
////        assertEquals("Exception occurred while fetching debit card details", response.getServiceStatus().getMessage());
////    }
////
////    @Test//pass
////    void handleExceptions() {
////        // Creating exception object
////        Exception exception = new Exception("Test exception");
////
////        // Invoking method under test
////        ResponseEntity<String> responseEntity = soapPhase.handleExceptions(exception);
////
////        // Asserting response status code and body
////        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
////        assertEquals("Test exception", responseEntity.getBody());
////    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////package cards.web.service.mybankdebitcardweb;
////
////import org.junit.jupiter.api.Test;
////import org.springframework.boot.test.context.SpringBootTest;
////
////@SpringBootTest
////class MybankDebitcardWebApplicationTests {
////
////    @Test
////    void contextLoads() {
////    }
////
////}
//
//
//
////    @Test//fail
////    void viewDebitCardResponse_Success() throws DebitCardException {
////        // Mocking debit card data
////        List<list.cards.mybankdebitcarddao.entities.DebitCard> debitCards = new ArrayList<>();
////        debitCards.add(new list.cards.mybankdebitcarddao.entities.DebitCard());
////
////        // Mocking DebitCardService to return debit card list
////        when(debitCardService.getDebitCard()).thenReturn(debitCards);
////
////        // Creating request object
////        ViewDebitCardRequest request = new ViewDebitCardRequest();
////
////        // Invoking method under test
////        ViewDebitCardResponse response = soapPhase.viewDebitCardResponse(request);
////
////        // Asserting response status
////        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
////    }