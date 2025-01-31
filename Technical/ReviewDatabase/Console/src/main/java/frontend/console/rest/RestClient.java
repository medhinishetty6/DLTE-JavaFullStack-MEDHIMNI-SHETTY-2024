package frontend.console.rest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class RestClient {
    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:7001/RestWebService/employees");
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    String json = EntityUtils.toString(response.getEntity());
                    System.out.println("Received JSON response:");
                    System.out.println(json); // Print the raw JSON response
                } else {
                    System.err.println("Failed to fetch data. Status code: " + statusCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//public class RestClient {
//    public static void main(String[] args) {
//        //CloseableHttpClient httpClient = HttpClients.createDefault();
//        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
//        HttpGet httpGet = new HttpGet("http://localhost:7001/RestWebService/employees");
//        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
//         //  HttpEntity entity = response.getEntity();
//            //if (entity != null) {
//               // String json = EntityUtils.toString(entity);
//                String json = EntityUtils.toString(response.getEntity());
//                System.out.println("Received JSON response:");
//                System.out.println(json); // Print the raw JSON response
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
////        } finally {
////            try {
////                httpClient.close();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//        }
//    }
//}
//
//
