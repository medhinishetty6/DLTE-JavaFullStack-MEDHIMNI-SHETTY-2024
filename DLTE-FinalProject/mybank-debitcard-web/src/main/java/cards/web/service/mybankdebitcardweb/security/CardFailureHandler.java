package cards.web.service.mybankdebitcardweb.security;

import list.cards.mybankdebitcarddao.security.CardSecurity;
import list.cards.mybankdebitcarddao.security.CardSecurityServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CardFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    CardSecurityServices cardSecurityServices;

    Logger logger= LoggerFactory.getLogger(CardFailureHandler.class);
    ResourceBundle resourceBundle= ResourceBundle.getBundle("application");

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        CardSecurity cardSecurity = cardSecurityServices.findByUserName(username);
        if(cardSecurity!=null){
            if (!cardSecurity.getCustomerStatus().equals("Inactive")) {
                if(cardSecurity.getAttempts()< cardSecurity.getMaxAttempt()){
                    cardSecurity.setAttempts(cardSecurity.getAttempts()+1);
                    cardSecurityServices.updateAttempts(cardSecurity);
                    logger.warn(resourceBundle.getString("credentials.invalid"));
                    exception=new LockedException(resourceBundle.getString("attempts.taken"));
                }
                else{
                    cardSecurityServices.updateStatus(cardSecurity);
                    logger.warn(resourceBundle.getString("account.suspend"));
                    exception=new LockedException(resourceBundle.getString("account.suspend"));
                }
            }
            else{
                logger.warn(resourceBundle.getString("account.redeem"));
            }
        }
        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }

}
