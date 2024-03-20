package restGetmapping;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.when;

public class AppTest {
    @Mock
    private UserInfoServices services;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private StringWriter stringWriter;
    @Mock
    private PrintWriter printWriter;


    @Before
    public void initiate() throws IOException {
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

    }

    @Test
    public void testFindAll() throws ServletException, IOException {
        transactionApi transaction=new transactionApi();
        transaction.userInfoServices=services;
        StringBuilder builder = new StringBuilder("Deposit,0,");
        builder.append(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        ArrayList<StringBuilder> transactionOne = new ArrayList<>();
        transactionOne.add(builder);
        Customer customer1 = new Customer("prasha02","prash321","karkala","prash@gmail.com",789267177L,1000000L,transactionOne);
        Customer customer2=new Customer("rakesh", "rak123", "Mangalore", "rak@gmail", 987455335L, 1000L,transactionOne);
        Customer customer3=new Customer("prash02", "prash321", "Mangalore", "rak@gmail", 987455335L, 1000L,transactionOne);
        List<Customer> customerList = Stream.of(customer1,customer2,customer3).collect(Collectors.toList());

        when(services.callFindAll()).thenReturn(customerList);
        transaction.doGet(request,response);

        verify(response).setContentType("application/json");

        verify(services).callFindAll();
        System.out.println(services.callFindAll());

        assertEquals("test: ","[Customer{username='prasha02', password='prash321', address='karkala', email='prash@gmail.com', contact=789267177, initialBalace=1000000, transactionDetails=[Deposit,0,19-03-2024]}, Customer{username='rakesh', password='rak123', address='Mangalore', email='rak@gmail', contact=987455335, initialBalace=1000, transactionDetails=[Deposit,0,19-03-2024]}, Customer{username='prash02', password='prash321', address='Mangalore', email='rak@gmail', contact=987455335, initialBalace=1000, transactionDetails=[Deposit,0,19-03-2024]}]",stringWriter.toString().trim());

    }

    @Test
    public void testFindUser() throws ServletException, IOException {
        transactionApi transaction=new transactionApi();
        transaction.userInfoServices=services;
        StringBuilder builder = new StringBuilder("Deposit,0,");
        builder.append(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        ArrayList<StringBuilder> transactionOne = new ArrayList<>();
        transactionOne.add(builder);
        Customer customer1 = new Customer("prasha02","prash321","karkala","prash@gmail.com",789267177L,1000000L,transactionOne);
        Customer customer2=new Customer("rakesh", "rak123", "Mangalore", "rak@gmail", 987455335L, 1000L,transactionOne);
        //  Customer customer3=new Customer("prash02", "prash321", "Mangalore", "rak@gmail", 987455335L, 1000L,transactionOne);
        List<Customer> customerList = Stream.of(customer1).collect(Collectors.toList());

        when(request.getParameter("username")).thenReturn("rakesh");
        when(services.callOneUserTransact(anyString())).thenReturn(customerList);
        transaction.doGet(request,response);

        verify(response).setContentType("application/json");

        verify(services).callOneUserTransact(anyString());
        //FAILS SINCE RAKESH USER IS TESTED WITH PRASHA02
        assertEquals("expected","[Customer{username='rakesh', password='rak123', address='Mangalore', email='rak@gmail', contact=987455335, initialBalace=1000, transactionDetails=[Deposit,0,19-03-2024]}]",stringWriter.toString().trim());
    }

    @Test
    public void findByDate() throws ServletException, IOException {
        transactionApi transaction=new transactionApi();
        transaction.userInfoServices=services;
        StringBuilder builder = new StringBuilder("Deposit,0,");
        builder.append(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        ArrayList<StringBuilder> transactionOne = new ArrayList<>();
        transactionOne.add(builder);
        Customer customer1 = new Customer("prasha02","prash321","karkala","prash@gmail.com",789267177L,1000000L,transactionOne);
        Customer customer2=new Customer("rakesh", "rak123", "Mangalore", "rak@gmail", 987455335L, 1000L,transactionOne);
        //  Customer customer3=new Customer("prash02", "prash321", "Mangalore", "rak@gmail", 987455335L, 1000L,transactionOne);
        List<Customer> customerList = Stream.of(customer1).collect(Collectors.toList());
        when(request.getParameter("username")).thenReturn("prasha02");
        when(request.getParameter("date")).thenReturn("19-03-2024");
        when(services.callTransactionByDate(anyString(),anyString())).thenReturn(customerList);
        transaction.doGet(request,response);
        verify(response).setContentType("application/json");
        verify(services).callTransactionByDate(anyString(),anyString());
        assertNotEquals("Expected","[Customer{username='prasha02', password='prash321', address='karkala', email='prash@gmail.com', contact=789267177, initialBalace=1000000, transactionDetails=[Deposit,0,19-03-2024]}",stringWriter.toString().trim());
    }

}
}
