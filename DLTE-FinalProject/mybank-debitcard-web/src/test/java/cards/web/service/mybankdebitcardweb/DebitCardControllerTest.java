//package cards.web.service.mybankdebitcardweb;
//
//import cards.web.service.mybankdebitcardweb.rest.DebitCardController;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import list.cards.mybankdebitcarddao.entities.DebitCard;
//import list.cards.mybankdebitcarddao.exception.DebitCardNullException;
//import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Date;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(DebitCardController.class)
//public class DebitCardControllerTest {
//
//    @MockBean
//    private DebitCardRepository debitCardRepository;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//
//    @Test
//    public void testActivateCard_Fail() throws Exception {
//        // Mock service method to throw a DebitCardNullException
//        when(debitCardRepository.activateStatus(any(DebitCard.class), any(Long.class)))
//                .thenThrow(new DebitCardNullException("Activation failed"));
//
//        // Mocking the request body
//        DebitCard debitCard = new DebitCard(); // Provide appropriate values for testing
//
//        // Performing the HTTP PUT request
//        mockMvc.perform(MockMvcRequestBuilders.put("/debitcard/activate/{cardNumber}", 1234567890)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(debitCard)))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.content().string("Activation failed"));
//    }
//
//
//
//    @Test
//    public void testActivateCard_Success() throws Exception {
//        // Mock DebitCard data
//        DebitCard mockDebitCard = new DebitCard();
//        mockDebitCard.setDebitCardNumber(3692468135796673L);
//        mockDebitCard.setAccountNumber(98789657098760L);
//        mockDebitCard.setCustomerId(123673);
//        mockDebitCard.setDebitCardCvv(786);
//        mockDebitCard.setDebitCardPin(8234);
//        mockDebitCard.setDebitCardExpiry(new Date("2025-02-09"));
//        mockDebitCard.setDebitCardStatus("Active");
//        mockDebitCard.setDomesticLimit(50000.0);
//        mockDebitCard.setInternationalLimit(200000.0);
//
//        // Mock service method to return a success message
//        String successMessage = "Debit card activation successful.";
//        when(debitCardRepository.activateStatus(any(DebitCard.class), any(Long.class))).thenReturn(successMessage);
//
//        // Performing the HTTP PUT request and verify the response
//        mockMvc.perform(MockMvcRequestBuilders.put("/debitcard/activate/{cardNumber}", 3692468135796673L)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(mockDebitCard)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(successMessage));
//    }
//
//    // Utility method to convert object to JSON string
//    private String asJsonString(Object obj) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(obj);
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
