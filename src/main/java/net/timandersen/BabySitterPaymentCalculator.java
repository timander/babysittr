package net.timandersen;

import sun.plugin.dom.exception.InvalidStateException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BabySitterPaymentCalculator {

    private final List<HourlyRate> hourlyRates = new ArrayList<HourlyRate>();

    public BabySitterPaymentCalculator(int bedTime) {
        for (int hour = 5; hour <= 16; hour++) {
            hourlyRates.add(new HourlyRate(hour, adjustTime(bedTime)));
        }
    }

    public int calculate(final int startTime, final int endTime) {
        int adjustedStartTime = adjustTime(startTime);
        int adjustedEndTime = adjustTime(endTime);
        int pay = 0;
        for (int i = adjustedStartTime; i < adjustedEndTime; i++) {
            pay += lookupRateFor(i);
        }
        return pay;
    }

    private int lookupRateFor(int hour) {
        for (HourlyRate hourlyRate : hourlyRates) {
            if (hourlyRate.getHour() == hour) return hourlyRate.getRate();
        }
        throw new InvalidStateException(hour + "");
    }

    private int adjustTime(int time) {
        return Arrays.asList(1, 2, 3, 4).contains(time) ? time + 12 : time;
    }

    private class HourlyRate {
        private final int STANDARD_RATE = 12;
        private final int AFTER_BEDTIME_RATE = 8;
        private final int AFTER_MIDNIGHT_RATE = 16;
        private final int MIDNIGHT = 12;

        private int hour;
        private int bedTime;

        private HourlyRate(int hour, int bedTime) {
            this.hour = hour;
            this.bedTime = bedTime;
        }

        int getHour() {
            return hour;
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

        int getRate() {
            if (afterBedtime() && beforeMidnight()) {
                return AFTER_BEDTIME_RATE;
            } else if (beforeBedtime() && beforeMidnight()) {
                return STANDARD_RATE;
            } else if (afterMidnight()) {
                return AFTER_MIDNIGHT_RATE;
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
