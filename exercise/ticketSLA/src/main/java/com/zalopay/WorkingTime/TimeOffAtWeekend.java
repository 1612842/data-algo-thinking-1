package com.zalopay.WorkingTime;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeOffAtWeekend extends TimeOffType{
    public TimeOffAtWeekend(LocalDateTime begin, LocalDateTime end) {
        super(begin, end);
    }

    @Override
    public Duration getDurationToMinus() {

        Duration duration=Duration.ofMinutes(0);
        int num=getNum();
        for (int i=0;i<num;i++){
            duration=duration.plusMinutes(270+480);
        }
        System.out.println("getDurationMinus() at weekend = "+duration.getSeconds()/60+" minutes");
        return duration;
    }

    @Override
    public int getNum() {
        int num = 0;
        num+=numWeekendBetweenToDateWorking(begin,end);
        System.out.println("getNum() at weekend = "+num);
        return num;
    }
}
