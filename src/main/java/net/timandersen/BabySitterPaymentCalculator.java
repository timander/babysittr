package net.timandersen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BabySitterPaymentCalculator {

    private final int STANDARD_RATE = 12;
    private final int AFTER_BEDTIME_RATE = 8;
    private final int AFTER_MIDNIGHT_RATE = 16;
    private int bedTime;
    private final Map<Integer, Integer> hourlyRates = new HashMap<Integer, Integer>();

    public BabySitterPaymentCalculator(int bedTime) {
        this.bedTime = adjustTime(bedTime);
        for (int hour = 5; hour <= 16; hour++) {
            if (beforeBedtime(hour) && beforeMidnight(hour)) {
                hourlyRates.put(hour, AFTER_BEDTIME_RATE);
            } else if (afterBedtime(hour) && beforeMidnight(hour)) {
                hourlyRates.put(hour, STANDARD_RATE);
            } else if (afterMidnight(hour)) {
                hourlyRates.put(hour, AFTER_MIDNIGHT_RATE);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private boolean afterMidnight(int hour) {
        return hour >= 12;
    }

    private boolean afterBedtime(int hour) {
        return hour < this.bedTime;
    }

    private boolean beforeMidnight(int hour) {
        return hour < 12;
    }

    private boolean beforeBedtime(int hour) {
        return hour >= this.bedTime;
    }

    public int calculate(final int startTime, final int endTime) {
        int adjustedStartTime = adjustTime(startTime);
        int adjustedEndTime = adjustTime(endTime);

        int pay = 0;
        for (int i = adjustedStartTime; i < adjustedEndTime; i++) {
            pay += hourlyRates.get(i);
        }
        return pay;
    }

    private int adjustTime(int time) {
        return Arrays.asList(1, 2, 3, 4).contains(time) ? time + 12 : time;
    }

}
