package com.zalopay.WorkingTime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class WorkingTime {
    ArrayList<TimeOffType> timeOffTypes;
    private LocalDateTime begin;
    private LocalDateTime end;


    public WorkingTime(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;

        timeOffTypes = new ArrayList<TimeOffType>();
        timeOffTypes.add(new TimeOffAtNoon(begin, end));
        timeOffTypes.add(new TimeOffAtNight(begin, end));
        timeOffTypes.add(new TimeOffAtWeekend(begin, end));
    }


    public Duration getWorkingTime() {
        Duration duration = Duration.between(begin, end);

        for (TimeOffType item : timeOffTypes) {
            duration = duration.minus(item.getDurationToMinus());
        }
        return duration;
    }

}

