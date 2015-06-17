package net.timandersen;

public class BabySitterPaymentCalculator {
    public int calculate(int startTime, int endTime) {
        return (endTime - startTime) * 12;
    }
}
