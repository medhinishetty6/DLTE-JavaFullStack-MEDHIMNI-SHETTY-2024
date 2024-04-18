package list.cards.mybankdebitcarddao;

import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import list.cards.mybankdebitcarddao.exception.DebitCardNullException;
import list.cards.mybankdebitcarddao.services.DebitCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.sql.Types;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DebitCardDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private DebitCardService debitCardService;

    Logger logger = LoggerFactory.getLogger(DebitCardService.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Test
    void testActivateStatus_Success() throws SQLException, DebitCardException, DebitCardNullException {
        // Mock the CallableStatementCreator
               when(jdbcTemplate.call(any(), anyList()))
                .thenReturn(createMockExecutionMap("SQLSUCESS"));

        // Call the method to be tested
        String result = debitCardService.activateStatus(new DebitCard(), 123L,567L);

        // Assertions
        assertEquals(resourceBundle.getString("card.active"),result);
    }

    @Test
    void testActivateStatus_DebitCardNotFoundException() throws SQLException {
        // Mock the CallableStatementCreator
        when(jdbcTemplate.call(any(), anyList()))
                .thenReturn(createMockExecutionMap("SQLERR-004"));

        // Call the method to be tested and assert that DebitCardNullException is thrown
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
        assertThrows(DebitCardNullException.class, () -> {
            debitCardService.activateStatus(new DebitCard(), 123456789L,34567L);
        });
    }

    // Helper method to create a mock execution map with a specified result
    private Map<String, Object> createMockExecutionMap(String result) {
        Map<String, Object> executionMap = new HashMap<>();
        executionMap.put("p_result", result); // Set the value of the output parameter
        return executionMap;
    }

}






































































//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////package list.cards.mybankdebitcarddao;
////
////
////import list.cards.mybankdebitcarddao.entities.DebitCard;
////import list.cards.mybankdebitcarddao.services.DebitCardService;
////import org.junit.Test;
////import org.junit.jupiter.api.extension.ExtendWith;
////import org.mockito.InjectMocks;
////import org.mockito.Mock;
////import org.mockito.junit.jupiter.MockitoExtension;
////import org.springframework.jdbc.core.CallableStatementCreator;
////import org.springframework.jdbc.core.JdbcTemplate;
////
////import java.util.HashMap;
////import java.util.Map;
////
////import static org.junit.jupiter.api.Assertions.assertEquals;
////import static org.mockito.ArgumentMatchers.any;
////import static org.mockito.ArgumentMatchers.anyList;
////import static org.mockito.Mockito.when;
////
////@ExtendWith(MockitoExtension.class)
////public class DebitCardDaoTest {
////
////    @Mock
////    private JdbcTemplate jdbcTemplate;
////
////    @InjectMocks
////    private DebitCardService debitCardService;
////
////    @Test
////    public void testActivateDebitCardStatus_Success() throws Exception {
////        // Create a mock DebitCard object with required attributes
////        DebitCard debitCard = new DebitCard();
////        debitCard.setDebitCardNumber(1234567890981234L);
////
////        // Mock the behavior of the jdbcTemplate.call() method to return a success execution map
////        Map<String, Object> returnedExecution = new HashMap<>();
////        returnedExecution.put("p_result", "SQLSUCCESS");
////        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);
////
////        // Call the method under test
////        String result = debitCardService.activateStatus(debitCard, 1234567890981234L);
////
////        // Verify that the result matches the expected result
////        assertEquals("Debit card activation successful.", result);
////    }
////
////    // Add more test cases for handling exceptions, etc.
////}
//
