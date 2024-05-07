package list.cards.mybankdebitcarddao;

import list.cards.mybankdebitcarddao.security.CardSecurity;
import list.cards.mybankdebitcarddao.security.CardSecurityServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DebitCardDaoSecurityTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CardSecurityServices cardSecurityServices;


    @Test
    void testMyBankCustomers() {
        CardSecurity customer = new CardSecurity();
        customer.setCustomerId(12367L);
        customer.setCustomerName("Akshatha");
        customer.setCustomerAddress("Bailoor");
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(9353523995L);
        customer.setUsername("Akshatha");
        customer.setPassword("nayak");
        customer.setAttempts(1);

        assertEquals(12367L, customer.getCustomerId());
        assertEquals("Akshatha", customer.getCustomerName());
        assertEquals("Bailoor", customer.getCustomerAddress());
        assertEquals("Active", customer.getCustomerStatus());
        assertEquals(9353523995L, customer.getCustomerContact());
        assertEquals("Akshatha", customer.getUsername());
        assertEquals("nayak", customer.getPassword());
        assertEquals(1, customer.getAttempts());
    }

    @Test
    void signingUp_Success() {
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName("prasha02");
        cardSecurity.setCustomerAddress("karkala");
        cardSecurity.setCustomerStatus("active");
        cardSecurity.setCustomerContact(1234567890L);
        cardSecurity.setUsername("john");
        cardSecurity.setPassword("prash321");
        cardSecurity.setAttempts(1);

        // Adjust stubbing to match the method invocation in the test
        when(jdbcTemplate.update(
                "insert into mybank_app_customer (CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD, ATTEMPTS) values(?,?,?,?,?,?,?)",
                "prasha02", "karkala", "active", 1234567890L, "john", "prash321", 1))
                .thenReturn(1);

        CardSecurity result = cardSecurityServices.signingUp(cardSecurity);

        assertEquals(cardSecurity, result);
        // Verify that the update method was called with the correct arguments
        verify(jdbcTemplate, times(1)).update(
                "insert into mybank_app_customer (CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD, ATTEMPTS) values(?,?,?,?,?,?,?)",
                "prasha02", "karkala", "active", 1234567890L, "john", "prash321", 1);
    }


    @Test
    void findByUserName_Exists() {
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName("prasha02");
        cardSecurity.setCustomerAddress("karkala");
        cardSecurity.setCustomerStatus("active");
        cardSecurity.setCustomerContact(1234567890L);
        cardSecurity.setUsername("john");
        cardSecurity.setPassword("prash321");
        cardSecurity.setAttempts(1);
        List<CardSecurity> customerList = Arrays.asList(cardSecurity);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(customerList);

        CardSecurity result = cardSecurityServices.findByUserName("john");

        assertEquals(cardSecurity, result);
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class));
    }

    @Test
    void findByUserName_NotFound() {
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(Arrays.asList());

        CardSecurity result = cardSecurityServices.findByUserName("john");

        assertNull(result);
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class));
    }

    @Test
    void updateAttempts_Success() {
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName("prasha02");
        cardSecurity.setCustomerAddress("karkala");
        cardSecurity.setCustomerStatus("active");
        cardSecurity.setCustomerContact(1234567890L);
        cardSecurity.setUsername("john");
        cardSecurity.setPassword("prash321");
        cardSecurity.setAttempts(1);
        when(jdbcTemplate.update(
                "update mybank_app_customer set attempts = ? where username = ?",
                1, "john"))
                .thenReturn(1);

        cardSecurityServices.updateAttempts(cardSecurity);

        verify(jdbcTemplate, times(1)).update(
                "update mybank_app_customer set attempts = ? where username = ?",
                1, "john");
    }


    @Test
    void updateStatus_Success() {
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName("prasha02");
        cardSecurity.setCustomerAddress("karkala");
        cardSecurity.setCustomerStatus("active");
        cardSecurity.setCustomerContact(1234567890L);
        cardSecurity.setUsername("john");
        cardSecurity.setPassword("prash321");
        cardSecurity.setAttempts(1);

        // Invoke the method under test
        cardSecurityServices.updateStatus(cardSecurity);

        // Verify that jdbcTemplate's update method was called with specific arguments
        verify(jdbcTemplate, times(1)).update(
                eq("update mybank_app_customer set customer_status = 'Inactive' where username = ?"),
                eq("john")
        );

        // Verify that no more interactions occurred with jdbcTemplate
        verifyNoMoreInteractions(jdbcTemplate);
    }



    @Test
    void getAccountOwnerUsername_Found() {
        Long accountNumber = 12345L;
        String expectedUsername = "john";

        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class))).thenReturn(expectedUsername);

        String result = cardSecurityServices.getAccountOwnerUsername(accountNumber);

        assertEquals(expectedUsername, result);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(Object[].class), eq(String.class));
    }

    @Test
    void getAccountOwnerUsername_NotFound() {
        Long accountNumber = 12345L;

        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class))).thenThrow(EmptyResultDataAccessException.class);

        String result = cardSecurityServices.getAccountOwnerUsername(accountNumber);

        assertNull(result);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(Object[].class), eq(String.class));
    }

    @Test
    void getAccountOwnerUsername_Exception() {
        Long accountNumber = 12345L;

        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class))).thenThrow(RuntimeException.class);

        String result = cardSecurityServices.getAccountOwnerUsername(accountNumber);

        assertNull(result);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(Object[].class), eq(String.class));
    }



    @Test
    void loadUserByUsername_NotFound() {
        assertThrows(UsernameNotFoundException.class, () -> cardSecurityServices.loadUserByUsername("john"));
    }


    @Test
    void filterByUserName_Found() {
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName("medh6");
        cardSecurity.setCustomerAddress("karkala");
        cardSecurity.setCustomerStatus("active");
        cardSecurity.setCustomerContact(1234567890L);
        cardSecurity.setUsername("john");
        cardSecurity.setPassword("medh6");
        cardSecurity.setAttempts(1);
        CardSecurity cardSecurityTwo = new CardSecurity();
        cardSecurityTwo.setCustomerName("medh6");
        cardSecurityTwo.setCustomerAddress("karkala");
        cardSecurityTwo.setCustomerStatus("active");
        cardSecurityTwo.setCustomerContact(1234567890L);
        cardSecurityTwo.setUsername("medh6");
        cardSecurityTwo.setPassword("password1");
        cardSecurityTwo.setAttempts(1);
        List<CardSecurity> customerList = Arrays.asList(
                cardSecurity,cardSecurityTwo
        );

        CardSecurity result = cardSecurityServices.filterByUserName(customerList, "john");

        assertEquals(customerList.get(0), result);
    }

    @Test
    void filterByUserName_NotFound() {
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName("prasha02");
        cardSecurity.setCustomerAddress("karkala");
        cardSecurity.setCustomerStatus("active");
        cardSecurity.setCustomerContact(1234567890L);
        cardSecurity.setUsername("john");
        cardSecurity.setPassword("prash321");
        cardSecurity.setAttempts(1);
        CardSecurity cardSecurityTwo = new CardSecurity();
        cardSecurityTwo.setCustomerName("prasha02");
        cardSecurityTwo.setCustomerAddress("karkala");
        cardSecurityTwo.setCustomerStatus("active");
        cardSecurityTwo.setCustomerContact(1234567890L);
        cardSecurityTwo.setUsername("prash");
        cardSecurityTwo.setPassword("prash321");
        List<CardSecurity> customerList = Arrays.asList(
                cardSecurity,cardSecurityTwo
        );

        CardSecurity result = cardSecurityServices.filterByUserName(customerList, "john");

        assertNotNull(result);
    }

}