package org.example;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.example.web.GroupOfTransaction;
import org.example.web.Transaction;
import org.example.web.TransactionSoap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import org.omg.IOP.TransactionService;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
@RunWith((MockitoJUnitRunner.class))
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Mock
    private TransactionService transactionService;
    private TransactionSoap transactionSoap;


    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Before
    public void setingUp() {
        transactionSoap = new TransactionSoap();
        transactionSoap.services = (TransactionService) transactionSoap;
    }

    @Test
    public void testApproval() {
        Transaction transaction1 = new Transaction(837563, "Medhini", new Date(2024 - 01 - 12), "Open", 900000, 20000);
        Transaction transaction2 = new Transaction(892633, "Meghana", new Date(2024 - 02 - 12), "Open", 889900, 700);
        transactionSoap.callFindAllTransaction(transaction1);
        transactionSoap.callFindAllTransaction(transaction1);
        verify(transactionService,times(1)).callSave(transaction1);
        Mockito.verify(transactionService, (VerificationMode) times(1)).getClass();

    }

    private Object verify(TransactionService transactionService, Object times) {
        return null;
    }

    private Object times(int i) {
        return null;
    }

    @Test
    public void testfindByTransactionDate() {
        String date = "2024-01-12";
        Transaction transaction1 = new Transaction(837563, "Medhini", new Date(2024 - 01 - 12), "Open", 900000, 20000);
        Transaction transaction2 = new Transaction(892633, "Meghana", new Date(2024 - 02 - 12), "Open", 889900, 700);
        Transaction transaction3 = new Transaction(892633, "Madan", new Date(2024 - 02 - 22), "Closed", 444400, 9900);
        List<Transaction> expectedList1 = Stream.of(transaction1, transaction2).collect(Collectors.toList());

        List<Transaction> expectedList2 = Stream.of(transaction1, transaction3).collect(Collectors.toList());

        when(transactionService.getClass(Transaction)).thenReturn(expectedList1);
        when(transactionService.getClass(Transaction)).thenReturn(expectedList2);
    }

        @Test
        public void testFindByUsername() {

            String user = "Medhini";
            Transaction transaction4 = new Transaction(837563, "Medhini", new Date(2024 - 01 - 12), "Open", 900000, 20000);
            Transaction transaction5 = new Transaction(892633, "Meghana", new Date(2024 - 02 - 12), "Open", 889900, 700);
            List<Transaction> expectedList = Stream.of(transaction4, transaction5).collect(Collectors.toList());
            when(transactionService.getClass(user)).thenReturn(expectedList);
            GroupOfTransaction groupOfTransaction = transactionService.toString(user);
            GroupOfTransaction groupOfTransaction = null;
            assertEquals("Medhini", groupOfTransaction.getTransaction().get(1).getTransactionDoneBy());
            assertTrue(groupOfTransaction.getTransaction().equals(expectedList));



    }
}
