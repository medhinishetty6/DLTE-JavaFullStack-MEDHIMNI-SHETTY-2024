package cards.web.service.mybankdebitcardweb.security;

import list.cards.mybankdebitcarddao.security.CardSecurity;
import list.cards.mybankdebitcarddao.security.CardSecurityServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CardSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    CardSecurityServices cardSecurityServices;

    Logger logger= LoggerFactory.getLogger(CardSuccessHandler.class);
    ResourceBundle resourceBundle= ResourceBundle.getBundle("card");

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CardSecurity cardSecurity= (CardSecurity) authentication.getPrincipal();
        if(!cardSecurity.getCustomerStatus().equals("Inactive")){
            if (cardSecurity.getAttempts() > 1) {
                cardSecurity.setAttempts(1);
                cardSecurityServices.updateAttempts(cardSecurity);
            }
            else if(cardSecurity.getCustomerStatus().equals("active")){
                super.setDefaultTargetUrl("/card/dashboard");
            }
            super.setDefaultTargetUrl("/card/dashboard");
        }
        else{
            logger.warn(resourceBundle.getString("account.redeem"));
            super.setDefaultTargetUrl("/card/login/?errors="+resourceBundle.getString("account.redeem"));
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}