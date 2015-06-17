package net.timandersen;

public class BabySitterPaymentCalculator {

    private final int BASE_RATE = 12;
    private final int AFTER_BEDTIME_DISCOUNT = 4;
    private final int AFTER_MIDNIGHT_PREMIUM = 4;
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

    private int adjustTime(int time) {
        return time >= 1 && time <= 4 ? time + 12: time;
    }

    private int basePay(int startTime, int endTime) {
        return (endTime - startTime) * BASE_RATE;
    }

    private int afterMidnightPremium(int startTime, int endTime) {
        int hoursAfterMidnight;
        if (startTime > 12) {
            hoursAfterMidnight = endTime - startTime;
        }
        else hoursAfterMidnight = endTime - 12;
        int afterMidnightPremium = 0;
        if (hoursAfterMidnight > 0) {
            afterMidnightPremium = hoursAfterMidnight * AFTER_MIDNIGHT_PREMIUM;
        }
        return afterMidnightPremium;
    }

    private int afterBedtimeDiscount(int startTime, int endTime) {
        int afterBedtimeDiscount = 0;
        if (startTime <= bedTime) {
            if (endTime > 12) endTime = 12;
            int hoursAfterBedtime = endTime - bedTime;
            if (hoursAfterBedtime > 0) {
                afterBedtimeDiscount = hoursAfterBedtime * AFTER_BEDTIME_DISCOUNT;
            }
        }
        return afterBedtimeDiscount;
    }

}
