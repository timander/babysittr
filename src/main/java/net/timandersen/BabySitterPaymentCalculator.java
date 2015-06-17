package net.timandersen;

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
            if (hour >= this.bedTime && hour < 12) {
                hourlyRates.put(hour, AFTER_BEDTIME_RATE);
            } else if (hour < this.bedTime && hour < 12) {
                hourlyRates.put(hour, STANDARD_RATE);
            } else if (hour >= 12) {
                hourlyRates.put(hour, AFTER_MIDNIGHT_RATE);
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
        return time >= 1 && time <= 4 ? time + 12 : time;
    }

}
