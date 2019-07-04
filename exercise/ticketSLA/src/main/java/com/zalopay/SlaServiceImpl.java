package com.zalopay;


import com.zalopay.WorkingTime.WorkingTime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by thinhda.
 * Date: 2019-04-15
 */

public class SlaServiceImpl implements SlaService {
    @Override
    public Duration calculate(LocalDateTime begin, LocalDateTime end) {
        // TODO: calc working hours


        WorkingTime workingTime =new WorkingTime(begin,end);

        System.out.println("Day of week begin: "+ begin.getDayOfWeek() +" "+ begin.getDayOfMonth()+" "+ begin.getMonth()+" "+ begin.getYear()+" ->"+begin.getHour()+":"+begin.getMinute());
        System.out.println("Day of week end: "+ end.getDayOfWeek() +" "+ end.getDayOfMonth()+" "+ end.getMonth()+" "+ end.getYear()+" ->"+end.getHour()+":"+end.getMinute());

        Duration duration = workingTime.getWorkingTime();


        System.out.println(duration.getSeconds()/60 + " minutes-----"+duration.getSeconds()/3600+" hours");
        System.out.println("-------------------------------------------------------------------------");
        return duration;
    }
}
