package net.timandersen;

public class BabySitterPaymentCalculator {

    private int bedTime;

    public BabySitterPaymentCalculator(int bedTime) {
        this.bedTime = bedTime;
    }

    public int calculate(int startTime, int endTime) {
        if (endTime >= 1 && endTime <= 4) endTime += 12;
        if (startTime >= 1 && startTime <= 4) startTime += 12;
        int totalHours = endTime - startTime;
        int afterBedtimeDiscount = afterBedtimeDiscount(startTime, endTime);
        int afterMidnightPremium = afterMidnightPremium(startTime, endTime);
        return totalHours * 12 - afterBedtimeDiscount + afterMidnightPremium;
    }

    private int afterMidnightPremium(int startTime, int endTime) {
        int hoursAfterMidnight = startTime > 12 ? endTime - startTime : endTime - 12;
        int afterMidnightPremium = 0;
        if (hoursAfterMidnight > 0) afterMidnightPremium = hoursAfterMidnight * 4;
        return afterMidnightPremium;
    }

    private int afterBedtimeDiscount(int startTime, int endTime) {
        int afterBedtimeDiscount = 0;
        if (startTime <= bedTime) {
            if (endTime > 12) endTime = 12;
            int hoursAfterBedtime = endTime - bedTime;
            if (hoursAfterBedtime > 0) afterBedtimeDiscount = hoursAfterBedtime * 4;
        }
        return afterBedtimeDiscount;
    }

}
