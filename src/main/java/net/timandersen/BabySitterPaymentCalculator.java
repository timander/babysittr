package net.timandersen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BabySitterPaymentCalculator {

    private final int STANDARD_RATE = 12;
    private final int AFTER_BEDTIME_RATE = 8;
    private final int AFTER_MIDNIGHT_RATE = 16;
    private final int MIDNIGHT = 12;
    private int bedTime;
    private final Map<Integer, Integer> hourlyRates = new HashMap<Integer, Integer>();

    public BabySitterPaymentCalculator(int bedTime) {
        this.bedTime = adjustTime(bedTime);
        for (int h = 5; h <= 16; h++) {
            Hour hour = new Hour(h);
            if (hour.afterBedtime() && hour.beforeMidnight()) {
                hourlyRates.put(h, AFTER_BEDTIME_RATE);
            } else if (hour.beforeBedtime() && hour.beforeMidnight()) {
                hourlyRates.put(h, STANDARD_RATE);
            } else if (hour.afterMidnight()) {
                hourlyRates.put(h, AFTER_MIDNIGHT_RATE);
            } else {
                throw new IllegalStateException();
            }
        }
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

    private class Hour {
        private int hour;

        Hour(int hour) {
            this.hour = hour;
        }

        boolean afterBedtime() {
            return hour >= bedTime;
        }

        boolean beforeBedtime() {
            return hour < bedTime;
        }

        boolean afterMidnight() {
            return hour >= MIDNIGHT;
        }

        boolean beforeMidnight() {
            return hour < MIDNIGHT;
        }
    }
}
