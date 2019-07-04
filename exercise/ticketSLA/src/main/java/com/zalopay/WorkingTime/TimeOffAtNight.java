package com.zalopay.WorkingTime;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeOffAtNight extends TimeOffType {
    public TimeOffAtNight(LocalDateTime begin, LocalDateTime end) {
        super(begin, end);
    }

    @Override
    public Duration getDurationToMinus() {

        Duration duration = Duration.ofMinutes(0);
        int num = getNum();
        for (int i = 0; i < num; i++) {
            duration = duration.plusMinutes(870);
        }
        System.out.println("getDurationMinus() at night = " + duration.getSeconds() / 60 + " minutes");
        return duration;
    }

    @Override
    public int getNum() {

        int num = 0;

        if (isAfternoonBegin(begin, beginWorkingAfternoonStart, beginWorkingAfternoonEnd)
                && isMorningEnd(end, endWorkingMorningStart, endWorkingMorningEnd))
            num += 1;

        num += numDayBetweenToDateWorking(begin, end);

        System.out.println("getNum() at night = " + num);
        return num;
    }
}
