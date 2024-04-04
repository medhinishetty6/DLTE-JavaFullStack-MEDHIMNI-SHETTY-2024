package cards.web.service.mybankdebitcardweb.soap;


import links.debitcard.DebitCard;
import links.debitcard.ServiceStatus;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import list.cards.mybankdebitcarddao.services.DebitCardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ComponentScan("list.cards.mybankdebitcarddao")
@Endpoint
public class SoapPhase {
    private final String url="http://debitcard.links";
    @Autowired
    private DebitCardService debitCardService;

    @PayloadRoot(namespace = url,localPart = "viewDebitCardRequest")
    @ResponsePayload
    public ViewDebitCardResponse viewDebitCardResponse(@RequestPayload ViewDebitCardRequest viewDebitCardRequest) throws SQLException {
        ViewDebitCardResponse viewDebitCardResponse = new ViewDebitCardResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<DebitCard> debitCardList = new ArrayList<>();

        List<list.cards.mybankdebitcarddao.entities.DebitCard> debitCardsDao = debitCardService.getDebitCard();

        Iterator<list.cards.mybankdebitcarddao.entities.DebitCard> iterator = debitCardsDao.iterator();

        while(iterator.hasNext()){
            links.debitcard.DebitCard currentDebitCard =new links.debitcard.DebitCard();
            BeanUtils.copyProperties(iterator.next(),currentDebitCard);
            debitCardList.add(currentDebitCard);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Debit Cards were fetched");

        viewDebitCardResponse.setServiceStatus(serviceStatus);
        viewDebitCardResponse.getDebitCard().addAll(debitCardList);

        return  viewDebitCardResponse;
    }

}
