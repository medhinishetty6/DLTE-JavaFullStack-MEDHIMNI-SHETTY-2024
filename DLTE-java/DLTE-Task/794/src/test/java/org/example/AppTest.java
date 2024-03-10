package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Date;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    MAIN main=new MAIN();
    public static void intialize(){
        Object loan;
        loan= Stream.of(new Loan(895648123L,900.00,new Date(2024,01,29),"Open","Medhini",8956231452L));
        new Loan(895009988L,800.00,new Date(2024,03,19),"Open","Medh",6864986588L);
        new Loan(456009988L,700.00,new Date(2024,02,19),"Closed","Medh",6864986588L);
    }
    public void testavail(){
        assertTrue(true);
    }

    private void assertTrue(boolean b) {
    }
}
