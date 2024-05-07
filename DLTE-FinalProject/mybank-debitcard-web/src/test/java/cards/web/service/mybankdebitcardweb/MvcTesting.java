package cards.web.service.mybankdebitcardweb;

import cards.web.service.mybankdebitcardweb.mvc.CardController;
import list.cards.mybankdebitcarddao.security.CardSecurity;
import list.cards.mybankdebitcarddao.security.CardSecurityServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URLEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MvcTesting {
    @InjectMocks
    private CardController cardController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardSecurityServices cardSecurityServices;

    @Mock
    private Authentication authentication;

    @Test
    @WithMockUser(username = "medh6")
    public void testIndex() {
        String indexName = cardController.index();
        assertEquals("index", indexName);
    }

    @Test
    @WithMockUser(username = "medh6")
    public void testDashboard() {
        String dashboardName = cardController.dashboard();
        assertEquals("dashboard", dashboardName);
    }

    @Test
    @WithMockUser(username = "medh6")
    public void testDebitCardDetails() {
        String viewName = cardController.debitCardDetails();
        assertEquals("view", viewName);
    }



    @Test
    @WithMockUser(username = "medh6")
    public void testError() {
        String error = cardController.error();
        assertEquals("error", error);
    }

    @Test
    @WithMockUser(username = "prasha02")
    public void testCustomerName() throws Exception {
        CardSecurity customer = new CardSecurity();
        customer.setCustomerName("");
        lenient().when(cardSecurityServices.findByUserName("prasha02")).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/name"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    @WithMockUser(username = "prasha02")
    public void testCustomername() throws Exception {
        // Mock the authentication object
        String userName = "prasha02";

        // Mock the SecurityContext and Authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        lenient().when(authentication.getName()).thenReturn(userName);

        // Mock the cardSecurityServices
        String expectedCustomerName = "";
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName(expectedCustomerName);
        lenient().when(cardSecurityServices.findByUserName(userName)).thenReturn(cardSecurity);

        // Perform GET request and verify response
        mockMvc.perform(get("/name"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(expectedCustomerName));
    }

    @Test
    @WithMockUser(username = "medh6")
    public void testCustomername_Exception() {
        // Mock authentication
        Authentication authentication = new TestingAuthenticationToken("user", "password");

        // Set the authentication in SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Mock cardSecurityServices
        CardSecurityServices cardSecurityServices = mock(CardSecurityServices.class);
        lenient().when(cardSecurityServices.findByUserName(anyString())).thenThrow(new RuntimeException("Simulated exception"));
    }
}
