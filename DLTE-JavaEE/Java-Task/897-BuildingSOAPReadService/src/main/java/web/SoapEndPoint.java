package web;

import javax.xml.ws.Endpoint;

public class SoapEndPoint {
    private static String url = "http://localhost:1010/findAllByType";

    public static void main(String[] args) {
        TransactionSoap transactionSoap = new TransactionSoap();
        System.out.println("Webservice hosted @ " + url);
        Endpoint.publish(url, transactionSoap);
    }
}
