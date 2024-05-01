package cards.web.service.mybankdebitcardweb;

import cards.web.service.mybankdebitcardweb.soap.SoapPhase;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import list.cards.mybankdebitcarddao.services.DebitCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MybankDebitcardWebApplicationTests {

    @MockBean
    private DebitCardService debitCardService;

    @InjectMocks
    private SoapPhase soapPhase;


@Test
public void testViewDebitCardResponse() throws DatatypeConfigurationException, DebitCardException, SQLException {

    List<list.cards.mybankdebitcarddao.entities.DebitCard> debitCardList = new ArrayList<>();
    list.cards.mybankdebitcarddao.entities.DebitCard entity = new list.cards.mybankdebitcarddao.entities.DebitCard();
    list.cards.mybankdebitcarddao.entities.DebitCard debitCard= new list.cards.mybankdebitcarddao.entities.DebitCard(8965767890967542L,99543456789125L,12345,234,1674,new java.util.Date(2024,04,9), "active", 2000.0,50000.0);
    //list.cards.mybankdebitcarddao.entities.DebitCard debitCard1 = new list.cards.mybankdebitcarddao.entities.DebitCard(8876945907634225L,89077956789126L,12346,564,2223,new java.util.Date(2024,04,11), "inactive", 4000.0,70000.0);
    //list.cards.mybankdebitcarddao.entities.DebitCard debitCard2 = new list.cards.mybankdebitcarddao.entities.DebitCard(8923167890123943L, 45601234567827L, 12347, 555, 6543, new java.util.Date(2024, 04, 04), "active", 3000.0, 60000.0);
    //debitCardList.add(debitCard);



    debitCardList.add(debitCard);
    when(debitCardService.getDebitCard()).thenReturn(debitCardList);

    ViewDebitCardRequest request = new ViewDebitCardRequest();

    ViewDebitCardResponse response = soapPhase.viewDebitCardResponse(request);

    // Verifying the response
    assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
    assertEquals(1, response.getDebitCard().size()); // Assuming one debit card is returned
}



    @Test
    public void testFailDebitCardResponse() throws DatatypeConfigurationException, DebitCardException, SQLException {
        // Mocking the DebitCardService response
        List<list.cards.mybankdebitcarddao.entities.DebitCard> debitCardList = new ArrayList<>();
        list.cards.mybankdebitcarddao.entities.DebitCard debitCard= new list.cards.mybankdebitcarddao.entities.DebitCard(8965767890967542L,99543456789125L,12345,234,1674,new java.util.Date(2024,04,9), "active", 2000.0,50000.0);
        list.cards.mybankdebitcarddao.entities.DebitCard debitCard1 = new list.cards.mybankdebitcarddao.entities.DebitCard(8876945907634225L,89077956789126L,12346,564,2223,new java.util.Date(2024,04,11), "inactive", 4000.0,70000.0);list.cards.mybankdebitcarddao.entities.DebitCard debitCard2 = new list.cards.mybankdebitcarddao.entities.DebitCard(8923167890123943L, 45601234567827L, 12347, 555, 6543, new java.util.Date(2024, 04, 04), "active", 3000.0, 60000.0);
        debitCardList.add(debitCard);
        debitCardList.add(debitCard1);
        when(debitCardService.getDebitCard()).thenReturn(debitCardList);

        ViewDebitCardRequest request = new ViewDebitCardRequest();

        // Calling the method
        //ViewDebitCardResponse response = soapPhase.viewDebitCardResponse(request);
        //assertNotEquals(1, response.getDebitCard().size()); // Assuming one debit card is returned
        assertNotEquals(123,debitCardList.get(1).getCustomerId());
    }

}



