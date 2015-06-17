package net.timandersen;

public class BabySitterPaymentCalculator {

    private int bedTime;

    public BabySitterPaymentCalculator(int bedTime) {
        this.bedTime = bedTime;
    }

    public int calculate(final int startTime, final int endTime) {
        int adjustedStartTime = adjustTime(startTime);
        int adjustedEndTime = adjustTime(endTime);
        return basePay(adjustedStartTime, adjustedEndTime)
                - afterBedtimeDiscount(adjustedStartTime, adjustedEndTime)
                + afterMidnightPremium(adjustedStartTime, adjustedEndTime);
    }

    private int adjustTime(int startTime) {
        return startTime >= 1 && startTime <= 4 ? startTime + 12: startTime;
    }

    private int basePay(int startTime, int endTime) {
        return (endTime - startTime) * 12;
    }

    private int afterMidnightPremium(int startTime, int endTime) {
        int hoursAfterMidnight;
        if (startTime > 12) {
            hoursAfterMidnight = endTime - startTime;
        }
        else hoursAfterMidnight = endTime - 12;
        int afterMidnightPremium = 0;
        if (hoursAfterMidnight > 0) {
            afterMidnightPremium = hoursAfterMidnight * 4;
        }
        return afterMidnightPremium;
    }

    private int afterBedtimeDiscount(int startTime, int endTime) {
        int afterBedtimeDiscount = 0;
        if (startTime <= bedTime) {
            if (endTime > 12) endTime = 12;
            int hoursAfterBedtime = endTime - bedTime;
            if (hoursAfterBedtime > 0) {
                afterBedtimeDiscount = hoursAfterBedtime * 4;
            }
        }
        return afterBedtimeDiscount;
    }

}
