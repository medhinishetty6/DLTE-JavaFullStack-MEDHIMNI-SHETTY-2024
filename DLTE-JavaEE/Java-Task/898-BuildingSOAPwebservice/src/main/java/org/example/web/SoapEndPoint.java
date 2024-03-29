package org.example.web;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.sql.SQLException;

public class SoapEndPoint {
    private static String url="http://localhost:1934/transaction";
    public static void main(String[] args) throws IOException, SQLException {
        TransactionSoap transactionSoap=new TransactionSoap();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url,transactionSoap);
    }
}
