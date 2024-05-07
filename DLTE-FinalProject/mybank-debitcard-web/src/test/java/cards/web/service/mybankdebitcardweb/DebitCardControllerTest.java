package cards.web.service.mybankdebitcardweb;
import cards.web.service.mybankdebitcardweb.rest.DebitCardController;
import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import list.cards.mybankdebitcarddao.remotes.DebitCardRepository;
import list.cards.mybankdebitcarddao.security.CardSecurity;
import list.cards.mybankdebitcarddao.security.CardSecurityServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.SQLSyntaxErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class DebitCardControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    DebitCardController debitCardController;

    @MockBean
    private DebitCardRepository debitCardRepository;
    @Mock
    private Authentication authentication;

    @MockBean
    private CardSecurityServices cardSecurityServices;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    @WithMockUser(username = "medhini")
    void testActivateCard_SuccessfulActivation() throws Exception {

        String request = "{\n" +
                "  \"debitCardNumber\": 3692468135796670,\n" +
                "  \"accountNumber\": 78909876543530,\n" +
                "  \"customerId\": 123670,\n" +
                "  \"debitCardCvv\": 123,\n" +
                "  \"debitCardPin\": 1234,\n" +
                "  \"debitCardExpiry\": \"2025-04-30\",\n" +
                "  \"debitCardStatus\": \"Inactive\",\n" +
                "  \"domesticLimit\": 1000,\n" +
                "  \"internationalLimit\": 50000\n" +
                "}";


        mockMvc.perform(put("/debitcard/activate/3692468135796670")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isUnauthorized());


    }

    @Test
    @WithMockUser(username = "medhini")
    void testActivateCard_LoginFail() throws Exception {

        String request = "{\n" +
                "  \"debitCardNumber\": 3692468135796670,\n" +
                "  \"accountNumber\": 78909876543530,\n" +
                "  \"customerId\": 123670,\n" +
                "  \"debitCardCvv\": 123,\n" +
                "  \"debitCardPin\": 1234,\n" +
                "  \"debitCardExpiry\": \"2025-04-30\",\n" +
                "  \"debitCardStatus\": \"Inactive\",\n" +
                "  \"domesticLimit\": 1000,\n" +
                "  \"internationalLimit\": 50000\n" +
                "}";


        when(debitCardRepository.activateStatus(any(),any(),any())).thenReturn("Debit card Activated");

        mockMvc.perform(put("/debitcard/activate/3692468135796670")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isUnauthorized());


    }


}
