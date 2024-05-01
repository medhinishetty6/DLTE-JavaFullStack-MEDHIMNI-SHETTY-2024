package list.cards.mybankdebitcarddao;

import list.cards.mybankdebitcarddao.entities.DebitCard;
import list.cards.mybankdebitcarddao.exception.DebitCardException;
import list.cards.mybankdebitcarddao.services.DebitCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MybankDebitcardDaoApplicationTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private DebitCardService debitCardService;


    @Test
    void testAll() throws SQLException {
        // Mocking the response from the database
        List<DebitCard> debitCardList = new ArrayList<>();


        DebitCard debitCard= new DebitCard(8965767890967542L,99543456789125L,12345,234,1674,new java.util.Date(2024,04,9), "active", 2000.0,50000.0);
        DebitCard debitCard1 = new DebitCard(8876945907634225L,89077956789126L,12346,564,2223,new java.util.Date(2024,04,11), "inactive", 4000.0,70000.0);
        DebitCard debitCard2 = new DebitCard(8923167890123943L, 45601234567827L, 12347, 555, 6543, new java.util.Date(2024, 04, 04), "active", 3000.0, 60000.0);
        DebitCard debitCard3 = new DebitCard(8796543210988877L, 23432109876528L, 12348, 877, 6782, new Date(2024, 04, 29), "blocked", 5000.0, 80000.0);
        //Add some dummy data into the arrayList for testing
        debitCardList = Stream.of(debitCard,debitCard1,debitCard2,debitCard3).collect(Collectors.toList());

        //Fetching the data from database
        when(jdbcTemplate.query(anyString(), ArgumentMatchers.any(DebitCardService.DebitCardMapper.class))).thenReturn(debitCardList);

        List<DebitCard> actualList = debitCardService.getDebitCard();

        assertSame(debitCardList.size(),actualList.size());//success
        // assertTrue(actualList.get(4)==debitCardList.get(4));//false Index out of bounds Exception
        assertNotSame(11111122343L,actualList.get(0).getCustomerId());//success

    }
    @Test
    void testGetDebitCardEmptyList() {
        // Mocking an empty response from the database
        when(jdbcTemplate.query(anyString(), ArgumentMatchers.any(DebitCardService.DebitCardMapper.class)))
                .thenReturn(new ArrayList<>())
                .thenThrow(new DebitCardException("Mocked Debit Card Exception") {});

        //If an exception of the specified type DebitCardException is thrown, the assertThrows method will pass; otherwise, it will fail.
        assertThrows(DebitCardException.class, () -> debitCardService.getDebitCard());
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
        List<DebitCard> actualList = debitCardService.getDebitCard();
        assertEquals(debitCardList.get(0).getDebitCardCvv(),actualList.get(1).getDebitCardCvv());
    }


}
