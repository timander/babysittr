package net.timandersen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BabySitterPaymentCalculatorTest {

    @Test
    public void babysitFromFiveToSixPm() throws Exception {
        BabySitterPaymentCalculator dollars = new BabySitterPaymentCalculator();
        assertEquals(12, dollars.calculate(5, 6));
    }

    @Test
    public void babysitFromFiveToSevenPm() throws Exception {
        BabySitterPaymentCalculator dollars = new BabySitterPaymentCalculator();
        assertEquals(24, dollars.calculate(5, 7));
    }

    @Test
    public void babysitFromFiveToMidnight() throws Exception {
        BabySitterPaymentCalculator dollars = new BabySitterPaymentCalculator();
        assertEquals(84, dollars.calculate(5, 12));
    }


}
