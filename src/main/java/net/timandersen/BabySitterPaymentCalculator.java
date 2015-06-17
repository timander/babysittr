package net.timandersen;

public class BabySitterPaymentCalculator {

    private int bedTime;

    public BabySitterPaymentCalculator(int bedTime) {
        this.bedTime = bedTime;
    }

    public int calculate(int startTime, int endTime) {
        int totalHours = endTime - startTime;
        int afterBedtimeDiscount = afterBedtimeDiscount(endTime);
        return totalHours * 12 - afterBedtimeDiscount;
    }

    private int afterBedtimeDiscount(int endTime) {
        int hoursAfterBedtime = endTime - bedTime;
        int afterBedtimeDiscount = 0;
        if (hoursAfterBedtime > 0) {
            afterBedtimeDiscount = hoursAfterBedtime * 4;
        }
        return afterBedtimeDiscount;
    }

}
