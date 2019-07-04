package com.zalopay.WorkingTime;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeOffAtNoon extends TimeOffType {

    public TimeOffAtNoon(LocalDateTime begin, LocalDateTime end) {
        super(begin, end);
    }

    @Override
    public Duration getDurationToMinus() {

        Duration duration=Duration.ofMinutes(0);
        int num=getNum();
        for (int i=0;i<num;i++){
            duration=duration.plusMinutes(90);
        }
        System.out.println("getDurationMinus() at noon = "+duration.getSeconds()/60+" minutes");
        return duration;
    }

    @Override
    public int getNum() {

        int num = 0;
        if (isMorningBegin(begin,beginWorkingMorningStart,beginWorkingMorningEnd)
        &&isAfternoonEnd(end,endWorkingAfternoonStart,endWorkingAfternoonEnd))
            num+=1;
        num+=numDayBetweenToDateWorking(begin,end);
        System.out.println("getNum() at noon = "+num);
        return num;
    }
}
