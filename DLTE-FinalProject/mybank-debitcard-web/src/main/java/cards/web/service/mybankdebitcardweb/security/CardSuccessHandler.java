//package cards.web.service.mybankdebitcardweb.security;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class CardSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    @Autowired
//    CardSecurityServices cardSecurityServices;
//
//    Logger logger= LoggerFactory.getLogger(CardSuccessHandler.class);
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        CardSecurity cardSecurity= (CardSecurity) authentication.getPrincipal();
//        if(cardSecurity.getCustomerStatus()!="block"){
//            if (cardSecurity.getAttempts() > 1) {
//                cardSecurity.setAttempts(1);
//                cardSecurityServices.updateAttempts(cardSecurity);
//            }
//           super.setAlwaysUseDefaultTargetUrl(true);
//        }
//        else{
//            logger.warn("Max attempts reached contact admin");
//            super.setDefaultTargetUrl("/login");
//        }
//        super.onAuthenticationSuccess(request, response, authentication);
//    }
//}