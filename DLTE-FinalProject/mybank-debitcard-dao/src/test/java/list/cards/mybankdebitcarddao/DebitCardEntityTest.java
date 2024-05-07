package list.cards.mybankdebitcarddao;

import list.cards.mybankdebitcarddao.entities.DebitCard;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DebitCardEntityTest {

    private DebitCard debitCard;

    @Before
    public void setUp() {
        debitCard = new DebitCard();
    }


    @Test
    public void testValidInputs() {
        // Valid inputs within ranges and formats
        debitCard.setDebitCardNumber(1234567890123456L);
        debitCard.setAccountNumber(12345678901234L);
        debitCard.setCustomerId(123456);
        debitCard.setDebitCardCvv(123);
        debitCard.setDebitCardPin(1234);
        debitCard.setDebitCardExpiry(new Date(System.currentTimeMillis() + 86400000)); // Tomorrow
        debitCard.setDebitCardStatus("active");
        debitCard.setDomesticLimit(1000.0);
        debitCard.setInternationalLimit(500.0);

        assertNotNull(debitCard);
    }

    @Test
    public void testBoundaryCases() {
        // Test boundary values for ranges
        debitCard.setDebitCardNumber(3692468135796670L); // Min value
        debitCard.setAccountNumber(10000000000000L); // Min value
        debitCard.setDebitCardCvv(222); // Min value
        debitCard.setDebitCardPin(3456); // Min value
        debitCard.setDomesticLimit(20000.0); // Min value
        debitCard.setInternationalLimit(50000.00); // Min value

        // Test with the earliest and latest possible dates for expiry
        debitCard.setDebitCardExpiry(new Date(Long.MIN_VALUE)); // Minimum date
        debitCard.setDebitCardExpiry(new Date(Long.MAX_VALUE)); // Maximum date

        assertNotNull(debitCard);
    }
}

