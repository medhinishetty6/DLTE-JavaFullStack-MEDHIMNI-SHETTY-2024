package cards.web.service.mybankdebitcardweb;

import cards.web.service.mybankdebitcardweb.security.CardFailureHandler;
import cards.web.service.mybankdebitcardweb.security.CardSuccessHandler;
import list.cards.mybankdebitcarddao.security.CardSecurity;
import list.cards.mybankdebitcarddao.security.CardSecurityServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoginHandlerTest {
    @Mock
    private CardSecurityServices cardSecurityServices;

    @InjectMocks
    private CardSuccessHandler  successHandler;

    @InjectMocks
    private CardFailureHandler failureHandler;

    @Test
    public void testAuthenticationFailureAttemptsExceeded() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException exception = new BadCredentialsException("Invalid credentials");

        String username = "testUser";
        CardSecurity myBankOfficials = new CardSecurity();
        myBankOfficials.setUsername(username);
        myBankOfficials.setCustomerStatus("Active"); // Assuming status allows authentication
        myBankOfficials.setAttempts(3); // Assuming maximum attempts are 3
        when(cardSecurityServices.findByUserName(username)).thenReturn(myBankOfficials);

        failureHandler.onAuthenticationFailure(request, response, exception);

        assertEquals("/card/login/?error=username does not exist",response.getRedirectedUrl());
    }

    @Test
    public void testAuthenticationFailureUserNotExists() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException exception = new UsernameNotFoundException("User not exists");

        String username = "nonExistingUser";
        when(cardSecurityServices.findByUserName(username)).thenReturn(null);

        failureHandler.onAuthenticationFailure(request, response, exception);

        assertEquals("/card/login/?error=username does not exist", response.getRedirectedUrl());
    }


    // success handler test
    @Test
    public void testMaxAttemptsReached() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Authentication authentication = mock(Authentication.class);
        CardSecurity myBankOfficials = new CardSecurity();
        myBankOfficials.setCustomerStatus("Inactive"); // Assuming status indicates maximum attempts reached
        when(authentication.getPrincipal()).thenReturn(myBankOfficials);

        successHandler.onAuthenticationSuccess(request, response, authentication);

        assertEquals("/card/login/?errors=Account suspended contact admin to redeem!", response.getRedirectedUrl());
    }


}