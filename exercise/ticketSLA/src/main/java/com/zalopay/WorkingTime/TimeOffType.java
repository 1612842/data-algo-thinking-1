package com.zalopay.WorkingTime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class TimeOffType {
    protected LocalDateTime begin;
    protected LocalDateTime end;

    protected LocalDateTime beginWorkingMorningStart;
    protected LocalDateTime beginWorkingMorningEnd;

    protected LocalDateTime beginWorkingAfternoonStart;
    protected LocalDateTime beginWorkingAfternoonEnd;

    protected LocalDateTime endWorkingMorningStart;
    protected LocalDateTime endWorkingMorningEnd;

    protected LocalDateTime endWorkingAfternoonStart;
    protected LocalDateTime endWorkingAfternoonEnd;


    public TimeOffType(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;

        beginWorkingMorningStart = LocalDateTime.of(begin.getYear(), begin.getMonth(), begin.getDayOfMonth(), 8, 30);
        beginWorkingMorningEnd = LocalDateTime.of(begin.getYear(), begin.getMonth(), begin.getDayOfMonth(), 12, 00);
        beginWorkingAfternoonStart = LocalDateTime.of(begin.getYear(), begin.getMonth(), begin.getDayOfMonth(), 13, 00);
        beginWorkingAfternoonEnd = LocalDateTime.of(begin.getYear(), begin.getMonth(), begin.getDayOfMonth(), 18, 00);

        endWorkingMorningStart = LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth(), 8, 30);
        endWorkingMorningEnd = LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth(), 12, 00);
        endWorkingAfternoonStart=LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth(), 13, 00);
        endWorkingAfternoonEnd= LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth(), 18, 00);

    }

    public int getNum(){
        return 0;
    }
    public Duration getDurationToMinus(){
        return  Duration.ofMinutes(0);
    }

    protected  long numDayBetweenToDateWorking(LocalDateTime begin, LocalDateTime end){
        long dayBetween =  ChronoUnit.DAYS.between(begin, end);
        return dayBetween;
    }

    protected long numWeekendBetweenToDateWorking(LocalDateTime begin, LocalDateTime end){
        long weekBetween =  ChronoUnit.WEEKS.between(begin, end);
        System.out.println("Day of week "+begin.getDayOfWeek().getValue());
        System.out.println("Day of week "+end.getDayOfWeek().getValue());
        if (end.getDayOfWeek().getValue()<begin.getDayOfWeek().getValue())
            weekBetween+=1;
        System.out.println("Weekend between: " + weekBetween);
        return weekBetween;
    }

    protected boolean isMorningBegin(LocalDateTime begin, LocalDateTime morningStart, LocalDateTime morningEnd) {
        if (begin.compareTo(morningStart) >= 0 && begin.compareTo(morningEnd) <= 0)
            return true;
        return false;
    }

    protected boolean isAfternoonBegin(LocalDateTime begin, LocalDateTime afternoonStart, LocalDateTime afternoonEnd) {
        if (begin.compareTo(afternoonStart) >= 0 && begin.compareTo(afternoonEnd) <= 0)
            return true;
        return false;
    }

    protected boolean isMorningEnd(LocalDateTime end, LocalDateTime morningStart, LocalDateTime morningEnd) {
        if (end.compareTo(morningStart) >= 0 && end.compareTo(morningEnd) <= 0)
            return true;
        return false;
    }

    protected boolean isAfternoonEnd(LocalDateTime end, LocalDateTime afternoonStart, LocalDateTime afternoonEnd) {
        if (end.compareTo(afternoonStart) >= 0 && end.compareTo(afternoonEnd) <= 0)
            return true;
        return false;
    }
}
