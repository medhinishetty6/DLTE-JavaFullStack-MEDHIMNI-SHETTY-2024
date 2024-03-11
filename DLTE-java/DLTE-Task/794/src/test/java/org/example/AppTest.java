package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
//    /**
//     * Rigorous Test :-)
//     */
//    @Test
//    public void shouldAnswerWithTrue()
//    {
//        assertTrue( true );
//    }
//    MAIN main=new MAIN();
    @BeforeClass
    public static void intialize(){
        Object loan = null;
        loan.add(new Loan(895648123L,900.00,new Date(2024,01,29),"Open","Medhini",8956231452L));
        loan.add(895009988L,800.00,new Date(2024,03,19),"Open","Medh",6864986588L);
        loan.add(456009988L,700.00,new Date(2024,02,19),"Closed","Medh",6864986588L);
    }
    @Test
    public void testavail(){
//        assertTrue(true);
    }

    private void assertTrue(boolean b) {
    }
}
