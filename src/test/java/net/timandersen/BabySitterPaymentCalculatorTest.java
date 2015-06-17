package net.timandersen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BabySitterPaymentCalculatorTest {

    @Test
    public void babysitFromFiveToSixPm() throws Exception {
        BabySitterPaymentCalculator dollars = new BabySitterPaymentCalculator();
        assertEquals(12, dollars.calculate(5, 6));
    }


}
