package cards.web.service.mybankdebitcardweb;

import cards.web.service.mybankdebitcardweb.security.CardSecurityApi;
import cards.web.service.mybankdebitcardweb.security.CardSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import list.cards.mybankdebitcarddao.security.CardSecurity;
import list.cards.mybankdebitcarddao.security.CardSecurityServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardSecurityApi.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CardSecurityServices customerServices;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Authentication authentication;
    @InjectMocks
    private CardSuccessHandler customerSuccessHandler;

    @Test
    @WithMockUser(username = "medh6")
    public void testOnAuthenticationSuccess_InactiveCustomer() throws Exception {
        // Arrange
        CardSecurity customer = new CardSecurity();
        customer.setCustomerStatus("Inactive");

        // Mock authentication principal
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(customer);
        // Mock request and response
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the behavior of response.isCommitted() to return false
        when(response.isCommitted()).thenReturn(false);

        // Mock the behavior of response.encodeRedirectURL()
        String expectedRedirectURL = "null/card/login/?errors=Account suspended contact admin to redeem!";
        when(response.encodeRedirectURL(anyString())).thenAnswer(invocation -> {
            String argument = invocation.getArgument(0);
            assertEquals(expectedRedirectURL, argument);
            return argument; // Return the same URL
        });

        // Act
        customerSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        // Assert
        Mockito.verify(response).encodeRedirectURL(expectedRedirectURL);
    }

}