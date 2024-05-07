package list.cards.mybankdebitcarddao;

import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.exception.CardNotEditableException;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import list.cards.mybankdebitcarddao.exception.DebitCardNullException;
import list.cards.mybankdebitcarddao.services.DebitCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MybankDebitcardDaoApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private DebitCardService debitCardService;

    @Autowired
    private MybankDebitcardDaoApplication application;

    @Mock
    private Logger logger;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(Collections.singletonList(new DebitCard()));
        when(jdbcTemplate.call(any(), any()))
                .thenReturn(Collections.singletonMap("p_result", "SQLSUCCESS"));
    }


    @Test
    void testActivateStatus_Success() throws SQLException, DebitCardException, DebitCardNullException {
        when(jdbcTemplate.call(any(), anyList()))
                .thenReturn(createMockExecutionMap("SQLSUCESS"));

        // Call the method to be tested
        String result = debitCardService.activateStatus(new DebitCard(), 123L,567L);

        assertEquals(resourceBundle.getString("card.active"),result);
    }

    @Test
    void testActivateStatus_DebitCardNotFoundException() throws SQLException {
        // Mock the CallableStatementCreator
        when(jdbcTemplate.call(any(), anyList()))
                .thenReturn(createMockExecutionMap("SQLERR-004"));

        // Call the method to be tested that DebitCardNullException is thrown
        assertThrows(DebitCardNullException.class, () -> {
            debitCardService.activateStatus(new DebitCard(), 369765432L,345678L);
        });
    }

    @Test
    void testActivateStatus_DebitCardAlreadyActiveException() throws SQLException {
        // Mock the behavior of JdbcTemplate's call method to return a specific result map
        when(jdbcTemplate.call(any(), anyList()))
                .thenReturn(createMockExecutionMap("SQLERR-005"));

        // Call the method under test and assert that DebitCardException is thrown
        assertThrows(DebitCardException.class, () -> {
            debitCardService.activateStatus(new DebitCard(), 123456789L,34567L);
        });
    }

    @Test
    void testActivateStatus_CardNotEditableException() {
        // Mock database call returning error code
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("p_result", "SQLERR-003");
        when(jdbcTemplate.call(any(), anyList())).thenReturn(resultMap);

        // Call method under test and verify exception
        assertThrows(CardNotEditableException.class, () -> debitCardService.activateStatus(new DebitCard(), 123L, 456L));
    }

    @Test
    void testAll() throws SQLException {
        // Mocking the response from the database
        List<DebitCard> debitCardList = new ArrayList<>();


        DebitCard debitCard= new DebitCard(8965767890967542L,99543456789125L,12345,234,1674,new java.util.Date(2024,04,9), "active", 2000.0,50000.0);
     //Add some dummy data into the arrayList for testing
        debitCardList = Stream.of(debitCard).collect(Collectors.toList());

        //Fetching the data from database
        when(jdbcTemplate.query(anyString(), ArgumentMatchers.any(DebitCardService.DebitCardMapper.class))).thenReturn(debitCardList);

        List<DebitCard> actualList = debitCardService.getDebitCard("medh6");

        assertSame(debitCardList.size(),actualList.size());//success
        assertNotSame(11111122343L,actualList.get(0).getDebitCardNumber());//success

    }


    @Test
    void testAllDebitCardsFail() throws SQLException {
        // Mocking the response from the database
        List<DebitCard> debitCardList = new ArrayList<>();

        DebitCard debitCard= new DebitCard(8965767890967542L,99543456789125L,12345,234,1674,new java.util.Date(2024,04,9), "active", 2000.0,50000.0);
        DebitCard debitCard1 = new DebitCard(8876945907634225L,89077956789126L,12346,234,2223,new java.util.Date(2024,04,11), "inactive", 4000.0,70000.0);
        DebitCard debitCard2 = new DebitCard(8923167890123943L, 45601234567827L, 12347, 555, 6543, new java.util.Date(2024, 04, 04), "active", 3000.0, 60000.0);
        DebitCard debitCard3 = new DebitCard(8796543210988877L, 23432109876528L, 12348, 877, 6782, new Date(2024, 04, 29), "blocked", 5000.0, 80000.0);
        //Add some dummy data into the arrayList for testing
        debitCardList = Stream.of(debitCard,debitCard1,debitCard2,debitCard3).collect(Collectors.toList());

        //Fetching the data from database
        when(jdbcTemplate.query(anyString(),ArgumentMatchers.any(DebitCardService.DebitCardMapper.class))).thenReturn(debitCardList);
        List<DebitCard> actualList = debitCardService.getDebitCard("medh6");
        assertNotEquals(debitCardList.get(0).getDebitCardCvv(),actualList.get(0).getDebitCardCvv());
    }


    @Test
    void testGetDebitCard_Success() {
        // Mock successful database query
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(Collections.singletonList(new DebitCard()));

        // Call method under test
        List<DebitCard> debitCards = debitCardService.getDebitCard("username");

        // Verify result
        assertFalse(debitCards.isEmpty());
    }

    @Test
    void testGetDebitCard_Exception() {
        // Mock database exception
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenThrow(new DataAccessException("Test Exception") {});

        // Call method under test and verify exception
        assertThrows(DebitCardException.class, () -> debitCardService.getDebitCard("username"));
    }

    @Test
    void testGetDebitCard_NoResult() {
        // Mock database query returning empty list
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(Collections.emptyList());

        // Call method under test and verify exception
        assertThrows(DebitCardNullException.class, () -> debitCardService.getDebitCard("username"));
    }





    @Test
    public void testResourceBundleContent() {
        // Test specific expected values and key
        assertEquals("Debit card list is null or empty", resourceBundle.getString("card.list.null"));
    }

    @Test
    void testAllDebitCards() throws SQLException {
        // Mocking the response from the database
        List<DebitCard> debitCardList = new ArrayList<>();

        //This object is used for updating the limits
        DebitCard debitCard = new DebitCard();
        debitCard.setDebitCardNumber(1234567890981234L);
        debitCard.setAccountNumber(78903456789123L);
        debitCard.setCustomerId(200005);
        debitCard.setDebitCardCvv(111);
        debitCard.setDebitCardPin(1234);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDate = calendar.getTime();

        debitCard.setDebitCardExpiry(expiryDate);
        debitCard.setDebitCardStatus("active");
        debitCard.setDomesticLimit(2000.0);
        debitCard.setInternationalLimit(5000.0);

        // Mock returned DebitCard fetched from the database
        DebitCard debitCardTwo = new DebitCard();
        debitCardTwo.setDebitCardNumber(1234567890981234L);
        debitCardTwo.setAccountNumber(78903456789123L);
        debitCardTwo.setCustomerId(200005);
        debitCardTwo.setDebitCardCvv(111);
        debitCardTwo.setDebitCardPin(1234);
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryUpdateDate = calendar.getTime();
        debitCardTwo.setDebitCardExpiry(expiryUpdateDate);
        debitCardTwo.setDebitCardStatus("active");
        debitCardTwo.setDomesticLimit(200000.0);
        debitCardTwo.setInternationalLimit(5000000.0);
        //Add some dummy data into the arrayList for testing
        debitCardList = Stream.of(debitCard, debitCardTwo).collect(Collectors.toList());

        //Fetching the data from database
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(DebitCardService.DebitCardMapper.class))).thenReturn(debitCardList);


        List<DebitCard> result = debitCardService.getDebitCard("prasha02");

        // Verify the exception message
        assertEquals(2, result.size());

        // Verify that jdbcTemplate.query() was called with the expected SQL query and arguments
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(DebitCardService.DebitCardMapper.class));
    }

    @Test
    void testAllDebitCardsSQLException() throws SQLException {

        // Define the expected exception message
        String expectedMessage = "SQL Syntax is Not proper try to resolve it";

        // Mock jdbcTemplate.query() to throw DebitCardException
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(DebitCardService.DebitCardMapper.class)))
                .thenThrow(new DebitCardException(expectedMessage));

        // Assert that calling debitCardServices.getDebitCard("prasha02") throws DebitCardException
        DebitCardException exception = assertThrows(DebitCardException.class, () -> debitCardService.getDebitCard("prasha02"));

        // Verify that the exception message is as expected
        assertEquals(expectedMessage, exception.getMessage());

        // Verify that jdbcTemplate.query() was called with the expected SQL query and arguments
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(DebitCardService.DebitCardMapper.class));
    }


    @Test
    void testGetDebitCardEmptyList() {
        // Mocking the response from the database
        List<DebitCard> debitCardList = new ArrayList<>();

        //This object is used for updating the limits
        DebitCard debitCard = new DebitCard();
        debitCard.setDebitCardNumber(1234567890981234L);
        debitCard.setAccountNumber(78903456789123L);
        debitCard.setCustomerId(200005);
        debitCard.setDebitCardCvv(111);
        debitCard.setDebitCardPin(1234);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDate = calendar.getTime();
        System.out.println(expiryDate);
        debitCard.setDebitCardExpiry(expiryDate);
        debitCard.setDebitCardStatus("block");
        debitCard.setDomesticLimit(2000.0);
        debitCard.setInternationalLimit(5000.0);

        // Mock returned DebitCard fetched from the database
        DebitCard debitCardTwo = new DebitCard();
        debitCardTwo.setDebitCardNumber(1234567890981234L);
        debitCardTwo.setAccountNumber(78903456789123L);
        debitCardTwo.setCustomerId(200005);
        debitCardTwo.setDebitCardCvv(111);
        debitCardTwo.setDebitCardPin(1234);
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryUpdateDate = calendar.getTime();
        debitCardTwo.setDebitCardExpiry(expiryUpdateDate);
        debitCardTwo.setDebitCardStatus("active");
        debitCardTwo.setDomesticLimit(200000.0);
        debitCardTwo.setInternationalLimit(5000000.0);
        //Add some dummy data into the arrayList for testing
        debitCardList = Stream.of(debitCard, debitCardTwo).collect(Collectors.toList());

        //Fetching the data from database
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(DebitCardService.DebitCardMapper.class))).thenThrow(new DebitCardException("No Debit cards available"));

        // Call the method under test with a sample customer ID and capture the exception
        DebitCardException exception = assertThrows(DebitCardException.class, () -> {
            debitCardService.getDebitCard("prasha02");
        });

        // Verify the exception message
        assertEquals("No Debit cards available", exception.getMessage());

        // Verify that jdbcTemplate.query() was called with the expected SQL query and arguments
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(DebitCardService.DebitCardMapper.class));
    }


    @Test
    public void testListAllCards() throws SQLException {

        // Mock ResultSet
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(1)).thenReturn(1234567890123456L);
        when(resultSet.getLong(2)).thenReturn(90876543212345L);
        when(resultSet.getInt(3)).thenReturn(123456);
        when(resultSet.getInt(4)).thenReturn(342);
        when(resultSet.getInt(5)).thenReturn(2312);

        // Create a java.util.Date object
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDateUtil = calendar.getTime();

        // Convert to java.sql.Date
        java.sql.Date expiryDateSql = new java.sql.Date(expiryDateUtil.getTime());

        // Stub the ResultSet to return the java.sql.Date
        when(resultSet.getDate(6)).thenReturn(expiryDateSql);

        when(resultSet.getString(7)).thenReturn("active");
        when(resultSet.getDouble(8)).thenReturn(50000.0);
        when(resultSet.getDouble(9)).thenReturn(80000.0);

        // Mock RowMapper
        DebitCardService.DebitCardMapper mapper = debitCardService.new DebitCardMapper();
        DebitCard debitCard = mapper.mapRow(resultSet, 1); // Ensure the index is appropriate

        // Assertions
        assertEquals(1234567890123456L, debitCard.getDebitCardNumber());
        assertEquals(90876543212345L, debitCard.getAccountNumber());
        assertEquals(123456, debitCard.getCustomerId());
        assertEquals(342, debitCard.getDebitCardCvv());
        assertEquals(2312, debitCard.getDebitCardPin());
        assertEquals(expiryDateSql, debitCard.getDebitCardExpiry()); // Ensure to compare with sql.Date
        assertEquals("active", debitCard.getDebitCardStatus());
        assertEquals(50000.0, debitCard.getDomesticLimit());
        assertEquals(80000.0, debitCard.getInternationalLimit());
    }


    @Test
    void testGetDebitCard() {
        List<DebitCard> debitCardList = debitCardService.getDebitCard("username");
        assertEquals(1, debitCardList.size());
    }

    @Test
    void testActivateStatus() throws SQLException, DebitCardException, DebitCardNullException, CardNotEditableException {
        DebitCard debitCard = new DebitCard();
        String result = debitCardService.activateStatus(debitCard, 1234556666675463L, 34523L);
        assertNotEquals("card.active", result);
    }

    // Helper method to create a mock execution map with a specified result
    private Map<String, Object> createMockExecutionMap(String result) {
        Map<String, Object> executionMap = new HashMap<>();
        executionMap.put("p_result", result); // Set the value of the output parameter
        return executionMap;
    }

}
