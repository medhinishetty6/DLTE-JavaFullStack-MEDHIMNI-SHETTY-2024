//package cards.web.service.mybankdebitcardweb;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.sql.SQLSyntaxErrorException;
//import java.util.ResourceBundle;
//
//import cards.web.service.mybankdebitcardweb.rest.DebitCardController;
//import list.cards.mybankdebitcarddao.entities.DebitCard;
//import list.cards.mybankdebitcarddao.exception.DebitCardException;
//import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
//import list.cards.mybankdebitcarddao.security.CardSecurity;
//import list.cards.mybankdebitcarddao.security.CardSecurityServices;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.TestingAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//public class DebitCardControllerTest {
//
//    @Mock
//    private DebitCardRepository debitCardRepository;
//
//    @Mock
//    private CardSecurityServices cardSecurityServices;
//
//    @InjectMocks
//    private DebitCardController debitCardController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testActivateCard_SuccessfulActivation() throws SQLSyntaxErrorException {
//        // Mocking
//        DebitCard debitCard = new DebitCard();
//        Long cardNumber = 123456789L;
//        Long customerId = 123L;
//
//        // Mocking SecurityContextHolder
//        Authentication authentication = new TestingAuthenticationToken("username", "password");
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
//        securityContext.setAuthentication(authentication);
//
//        CardSecurity cardSecurity = new CardSecurity();
//        cardSecurity.setCustomerId(customerId);
//        when(cardSecurityServices.findByUserName("username")).thenReturn(cardSecurity);
//
//        // Stubbing the method call on the mock object using when().thenReturn() syntax
//        when(debitCardRepository.activateStatus(any(DebitCard.class), anyLong(), anyLong())).thenReturn("Debit card activation successful.");
//
//        // Call the method under test
//        ResponseEntity<String> response = debitCardController.activateCard(debitCard, cardNumber);
//
//        // Assertions
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Debit card activation successful.", response.getBody());
//    }
//



//    @Test
//    public void testActivateCard_CardAlreadyActive() throws SQLSyntaxErrorException {
//        // Mocking
//        DebitCard debitCard = new DebitCard();
//        Long cardNumber = 123456789L;
//
//        // Mocking SecurityContextHolder
//        Authentication authentication = new TestingAuthenticationToken("username", "password");
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
//        securityContext.setAuthentication(authentication);
//
//        when(cardSecurityServices.findByUserName("username")).thenReturn(new CardSecurity());
//
//        when(debitCardRepository.activateStatus(any(DebitCard.class), anyLong(), anyLong()))
//                .thenThrow(new DebitCardException("error"));
//
//        // Call the method under test
//        ResponseEntity<String> response = debitCardController.activateCard(debitCard, cardNumber);
//
//        // Assertions
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Error message", response.getBody());
//    }
//}













































//import java.sql.SQLSyntaxErrorException;
//import java.util.ResourceBundle;
//
//import cards.web.service.mybankdebitcardweb.rest.DebitCardController;
//import list.cards.mybankdebitcarddao.entities.DebitCard;
//import list.cards.mybankdebitcarddao.exception.DebitCardException;
//import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//public class DebitCardControllerTest {
//
//    @Mock
//    private DebitCardRepository debitCardRepository;
//
//
//    @InjectMocks
//    private DebitCardController debitCardController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testActivateCard_SuccessfulActivation() throws SQLSyntaxErrorException {
//        // Mocking
//        DebitCard debitCard = new DebitCard(); // create a valid DebitCard object
//        Long cardNumber = 123456789L;
//        Long cardId=741741L;
//        when(debitCardRepository.activateStatus(any(DebitCard.class), anyLong(),78966425L)).thenReturn("Debit card activation successful.");
//
//        // Call the method under test
//        ResponseEntity<String> response = debitCardController.activateCard(debitCard, cardNumber);
//
//        // Assertions
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Debit card activation successful.", response.getBody());
//    }
//
//    @Test
//    public void testActivateCard_CardAlreadyActive() {
//        // Mocking
//        DebitCard debitCard = new DebitCard(); // create a valid DebitCard object
//        Long cardNumber = 123456789L;
//        when(debitCardRepository.activateStatus(any(DebitCard.class), anyLong())).thenThrow(new DebitCardException("message error detected"));
//        when(resourceBundle.getString(anyString())).thenReturn("Error message");
//
//        // Call the method under test
//        ResponseEntity<String> response = debitCardController.activateCard(debitCard, cardNumber);
//
//        // Assertions
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Error message", response.getBody());
//    }
//
//     Add more test cases for other scenarios like empty body, internal error, etc.
//}
//






























































//
//import list.cards.mybankdebitcarddao.entities.DebitCard;
//import list.cards.mybankdebitcarddao.exception.DebitCardException;
//import list.cards.mybankdebitcarddao.exception.DebitCardNullException;
//import list.cards.mybankdebitcarddao.services.DebitCardService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.MediaType;
//import org.springframework.jdbc.core.CallableStatementCreator;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.sql.Types;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.ResourceBundle;
//import java.util.logging.Logger;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class DebitCardControllerTest {
//
//    @InjectMocks
//    private DebitCardService debitCardService;
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @Mock
//    private Logger logger;
//
//    @Mock
//    private ResourceBundle resourceBundle;
//
////    Logger logger = LoggerFactory.getLogger(DebitCardService.class);
////    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
//    private MockMvc mockMvc;
//    @Test
//    public void testActivateStatus_Success() throws Exception {
//        // Mock input data
//        Long debitCardNumber = 1234567890123456L;
//        DebitCard debitCard = new DebitCard();
//
//        // Mock database call
//        when(jdbcTemplate.call(Mockito.any(CallableStatementCreator.class), Mockito.anyList()))
//                .thenReturn(createSuccessResultMap());
//
//        // Mock resource bundle
//        when(resourceBundle.getString("card.active")).thenReturn("Debit card activation successful");
//
//        // Invoke the method
//        String result = debitCardService.activateStatus(debitCard, debitCardNumber);
//
//        // Verify the result
//        assertEquals("Debit card activation successful", result);
//    }
//
//    @Test
//    public void testingActivateStatus_DebitCardException() {
//        // Mock input data
//        Long debitCardNumber = 1234567890123456L;
//        DebitCard debitCard = new DebitCard();
//
//        // Mock database call
//        when(jdbcTemplate.call(Mockito.any(CallableStatementCreator.class), Mockito.anyList()))
//                .thenReturn(createErrorResultMap("SQLERR-005"));
//
//        // Mock the method that fetches the resource bundle
//        //Mockito.when(debitCardService.getResourceBundle()).thenReturn(resourceBundleMock);
//
//        // Mock resource bundle
//        ResourceBundle resourceBundleMock = Mockito.mock(ResourceBundle.class);
//        when(resourceBundleMock.getString("debitCard.already.active")).thenReturn("Debit card is already active.");
//
//        // Invoke the method and verify exception
//        DebitCardException exception = assertThrows(DebitCardException.class,
//                () -> debitCardService.activateStatus(debitCard, debitCardNumber));
//        assertEquals("Debit card is already active.", exception.getMessage());
//    }
//
////    @Test
//    public void testActivateCard_Success() throws Exception {
//        // Mock debit card data
//        DebitCard debitCard = new DebitCard();
//        debitCard.setDebitCardNumber(123456789012345L);
//
//        // Mock repository method
//        when(debitCardService.activateStatus(debitCard, 1234567890123456L))
//                .thenReturn("Debit card activation successful.");
//
//        // Mock resource bundle
//        when(resourceBundle.getString("card.active")).thenReturn("Debit card activated successfully.");
//
//        // Perform POST request
//        mockMvc.perform(MockMvcRequestBuilders.put("/debitcard/activate/1234567890123456")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(debitCard)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Debit card activated successfully."));
//    }
//
//
//
//    @Test
//    public void testActivateStatus_DebitCardException() {
//        // Mock input data
//        Long debitCardNumber = 1234567890123456L;
//        DebitCard debitCard = new DebitCard();
//
//        // Mock database call
//        when(jdbcTemplate.call(Mockito.any(CallableStatementCreator.class), Mockito.anyList()))
//                .thenReturn(createErrorResultMap("SQLERR-005"));
//
//        // Mock resource bundle
//        ResourceBundle resourceBundle = Mockito.mock(ResourceBundle.class);
//        when(resourceBundle.getString("debitCard.already.active")).thenReturn("Debit card is already active.");
//
//        // Inject the mocked resource bundle into the service
//
//
//        // Invoke the method and verify exception
//        DebitCardException exception = assertThrows(DebitCardException.class,
//                () -> debitCardService.activateStatus(debitCard, debitCardNumber));
//        assertEquals("Debit card is already active.", exception.getMessage());
//    }
//
//
//    private Map<String, Object> createSuccessResultMap() {
//        // Simulate success result map from database call
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("p_result", "SQLSUCESS");
//        return resultMap;
//    }
//
//    private Map<String, Object> createErrorResultMap(String errorCode) {
//        // Simulate error result map from database call
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("p_result", errorCode);
//        return resultMap;
//    }
//}

















//    @Test
//    public void testActivateCard_Success() throws Exception {
//        // Mock service method to return a valid response
//        String successMessage = "Debit card activation successful.";
//        when(debitCardRepository.activateStatus(any(DebitCard.class), any(Long.class))).thenReturn(successMessage);
//
//        // Mocking the request body
//        DebitCard debitCard = new DebitCard(); // Provide appropriate values for testing
//
//        // Performing the HTTP PUT request
//        mockMvc.perform(MockMvcRequestBuilders.put("/debitcard/activate/{cardNumber}", 1234567890)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(debitCard)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(successMessage));
//    }


































//
//import cards.web.service.mybankdebitcardweb.rest.DebitCardController;
//import list.cards.mybankdebitcarddao.entities.DebitCard;
//import list.cards.mybankdebitcarddao.services.DebitCardService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Date;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//public class DebitCardControllerTest {
//    @MockBean
//    private DebitCardService debitCardService;
//
//    @InjectMocks
//    private DebitCardController debitCardController;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//    @Test
//    void testApproval() throws Exception {
//        String request = "{\n" +
//                "   \"debitCardNumber\":3692468135796673,\n" +
//                "  \"accountNumber\":98789657098760,\n" +
//                "  \"customerId\":123673,\n" +
//                "  \"debitCardCvv\":786,\n" +
//                "  \"debitCardPin\":8234,\n" +
//                "  \"debitCardExpiry\":\"2025-02-09\",\n" +
//                "  \"debitCardStatus\":\"Active\",\n" +
//                "  \"domesticLimit\":50000,\n" +
//                "  \"internationalLimit\":200000\"\n" +
//                " }";
//
//        DebitCard debitCard=new DebitCard(7363783399376234L,74652453648446L,20001,123,1234,new Date("10/12/2024"),"Inactive",20000.0,500000.00);
//        when(debitCardService.activateStatus(any(), anyLong())).thenReturn(debitCard);
//    }
//
//
//    }
