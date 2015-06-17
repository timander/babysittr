package net.timandersen;

public class BabySitterPaymentCalculator {

    private int bedTime;

    public BabySitterPaymentCalculator(int bedTime) {
        this.bedTime = bedTime;
    }

    public int calculate(int startTime, int endTime) {
        if (endTime >= 1 && endTime <= 4) {
            endTime += 12;
        }
        int totalHours = endTime - startTime;
        int afterBedtimeDiscount = afterBedtimeDiscount(endTime);
        int afterMidnightPremium = afterMidnightPremium(endTime);
        return totalHours * 12 - afterBedtimeDiscount + afterMidnightPremium;
    }

    private int afterMidnightPremium(int endTime) {
        int hoursAfterMidnight = endTime - 12;
        int afterMidnightPremium = 0;
        if (hoursAfterMidnight > 0) {
            afterMidnightPremium = hoursAfterMidnight * 4;
        }
        return afterMidnightPremium;
    }

    private int afterBedtimeDiscount(int endTime) {
        if (endTime > 12) endTime = 12;
        int hoursAfterBedtime = endTime - bedTime;
        int afterBedtimeDiscount = 0;
        if (hoursAfterBedtime > 0) {
            afterBedtimeDiscount = hoursAfterBedtime * 4;
        }
        return afterBedtimeDiscount;
    }

}
