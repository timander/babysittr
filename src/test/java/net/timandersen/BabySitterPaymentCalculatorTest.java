package net.timandersen;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BabySitterPaymentCalculatorTest {

    private BabySitterPaymentCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new BabySitterPaymentCalculator(9);
    }

    @Test
    public void babysitFromFiveToSixPm() throws Exception {
        assertEquals(12, calculator.calculate(5, 6));
    }

    @Test
    public void babysitFromFiveToSevenPm() throws Exception {
        assertEquals(24, calculator.calculate(5, 7));
    }

    @Test
    public void babysitFromFiveToMidnightWithBedtimeAtNinePm() throws Exception {
        assertEquals(72, calculator.calculate(5, 12));
    }

    @Test
    @Ignore
    public void babysitFromFivePmToFourAmWithBedtimeAtNinePm() throws Exception {
        assertEquals(136, calculator.calculate(5, 4));
    }


}
