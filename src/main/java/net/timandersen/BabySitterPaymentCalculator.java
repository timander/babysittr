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
        for (int hour = 5; hour <= 16; hour++) {
            if (after(hour, this.bedTime) && before(hour, MIDNIGHT)) {
                hourlyRates.put(hour, AFTER_BEDTIME_RATE);
            } else if (before(hour, this.bedTime) && before(hour, MIDNIGHT)) {
                hourlyRates.put(hour, STANDARD_RATE);
            } else if (after(hour, MIDNIGHT)) {
                hourlyRates.put(hour, AFTER_MIDNIGHT_RATE);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private boolean after(int hour, int time) {
        return hour >= time;
    }

    private boolean before(int hour, int time) {
        return hour < time;
    }

    public int calculate(final int startTime, final int endTime) {
        int adjustedStartTime = adjustTime(startTime);
        int adjustedEndTime = adjustTime(endTime);

        int pay = 0;
        for (int i = adjustedStartTime; before(i, adjustedEndTime); i++) {
            pay += hourlyRates.get(i);
        }
        return pay;
    }

    private int adjustTime(int time) {
        return Arrays.asList(1, 2, 3, 4).contains(time) ? time + 12 : time;
    }

}
